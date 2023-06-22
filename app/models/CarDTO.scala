package models

import play.api.libs.json.{Format, Json, OFormat}

case class CarDTO(id: Option[Long], brand: String, model: String, cc: Int)

object CarDTO{
  implicit val format: Format[CarDTO] = Json.format[CarDTO]
}
