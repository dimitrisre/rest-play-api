package controllers

import managers.CarManager
import models.CarDTO
import play.api.mvc.ControllerComponents

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class ConsoleController (val carManager: CarManager)(implicit val ec: ExecutionContext) {
  def start(): Unit = {
    var choice = 1
    do {
      dumpChoices()
      choice = scala.io.StdIn.readInt()
      choice match {
        case 1 =>  carManager.getAll().fold(e => println(s"Error: ${e.message}"), cars => cars.foreach(println))
        case 2 =>  carManager.save(readCarValues())
        case 3 =>  updateCarValues()
        case 4 =>  deleteCar()
      }
    }while(choice != 10)
  }

  def stop(): Future[_] = Future.successful(())

  def dumpChoices(): Unit = {
    println(
      """1. Dump the database
        |2. Insert a new car
        |3. Update an existing car
        |4. Delete a car
        |10. Exit
        |""".stripMargin)
  }

  def readCarValues(): CarDTO = {
    println("Enter car brand:")
    val brand = scala.io.StdIn.readLine()
    println("Enter car model:")
    val model = scala.io.StdIn.readLine()
    println("Enter car cc:")
    val cc = scala.io.StdIn.readInt()

    CarDTO(None, brand, model, cc)
  }

  def updateCarValues(): Unit = {
    val carId = Try(scala.io.StdIn.readLong()) match {
      case Success(value) => value
      case Failure(exception) =>
        println(exception.getMessage)
        return ()
    }

    val carDTOOpt = carManager.get(carId).getOrElse(None)

    carDTOOpt.map { carDTO =>
      println("Enter car brand:")
      val brand = Option(scala.io.StdIn.readLine()).filter(_.nonEmpty)
      println("Enter car model:")
      val model = Option(scala.io.StdIn.readLine()).filter(_.nonEmpty)
      println("Enter car cc:")
      val cc = Try(scala.io.StdIn.readInt()).toOption

      val brandUpdated = brand.map(b => carDTO.copy(brand = b)) orElse Some(carDTO)
      val modelUpdated = model.flatMap(m => brandUpdated.map(_.copy(model = m))) orElse brandUpdated
      val ccUpdated = cc.flatMap(c => modelUpdated.map(_.copy(cc = c))) orElse modelUpdated

      carManager.update(ccUpdated.get)
    }
  }

  def deleteCar(): Unit = {
    val carId = Try(scala.io.StdIn.readLong()) match {
      case Success(value) => value
      case Failure(exception) =>
        println(exception.getMessage)
        return ()
    }

    carManager.get(carId).getOrElse(None).map(carManager.delete)
  }
}
