package persistence.entities

import models.CarDTO

case class Car(id: Long, brand: String, model: String, cc: Int)

object Car{
  implicit class CarOps(car: Car) {
    def fromDTO(carDTO: CarDTO): Car = Car(carDTO.id.getOrElse(0L), carDTO.brand, carDTO.model, carDTO.cc)

    def toDTO(): CarDTO = CarDTO(Some(car.id), car.brand, car.model, car.cc)
  }
}
