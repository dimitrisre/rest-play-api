package models

import persistence.entities.Car
import play.api.libs.json.{Format, Json}

case class CarDTO(id: Option[Long], brand: String, model: String, cc: Int)

object CarDTO{
  implicit val format: Format[CarDTO] = Json.format[CarDTO]

  implicit class CarDTOOps(carDTO: CarDTO) {
    def fromDB(car: Car): CarDTO = CarDTO(Some(car.id), car.brand, car.model, car.cc)

    def toDB(): Car = Car(carDTO.id.getOrElse(0L), carDTO.brand, carDTO.model, carDTO.cc)
  }
}
