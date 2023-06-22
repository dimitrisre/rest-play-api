package controllers

import controllers.actions.CarActions
import play.api.mvc.{BaseController, ControllerComponents, PlayBodyParsers}
import managers.CarManager
import models.CarDTO
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext

class HomeController(val carManager: CarManager, val controllerComponents: ControllerComponents)(implicit val ec: ExecutionContext)
  extends BaseController
    with CarActions{
  def get(id: Long) = Action{
    carManager
      .get(id)
      .fold(
        error => BadRequest(error.message),
        result => result.map(car => Ok(Json.toJson(car))).getOrElse(NoContent)
      )
  }

  def getAll() = Action {
    carManager
      .getAll()
      .fold(
        error => BadRequest(error.message),
        result => Ok(Json.toJson(result))
      )
  }

  def update() = Action(parse.json[CarDTO]){ implicit request =>
      carManager
      .update(request.body)
      .fold(
        error => BadRequest(error.message),
        _ => NoContent
      )
  }

  def delete(id: Long) = CarAction(id){ implicit request =>
    request.carDTO.map(car =>
    carManager
      .delete(car)
      .fold(
        error => BadRequest(error.message),
        _ => NoContent
      )
    ).getOrElse(BadRequest)
  }

  def save() = Action(parse.json[CarDTO]){ implicit request =>
    carManager
      .save(request.body)
      .fold(
        error => BadRequest(error.message),
        car => Ok(Json.toJson(car))
      )
  }

  def index() = Action{
    Ok("This is a test")
  }

  override def playBodyParsers: PlayBodyParsers = controllerComponents.parsers
}
