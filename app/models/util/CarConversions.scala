package models.util

import models.CarDTO
import persistence.entities.Car


object CarConversions {
  implicit class CarDTOOps(carDTO: CarDTO) {
    def fromDB(car: Car): CarDTO = CarDTO(Some(car.id), car.brand, car.model, car.cc)

    def toDB(): Car = Car(carDTO.id.getOrElse(0L), carDTO.brand, carDTO.model, carDTO.cc)
  }

  implicit class CarOps(car: Car) {
    def fromDTO(carDTO: CarDTO): Car = Car(carDTO.id.getOrElse(0L), carDTO.brand, carDTO.model, carDTO.cc)

    def toDTO(): CarDTO = CarDTO(Some(car.id), car.brand, car.model, car.cc)
  }
}
