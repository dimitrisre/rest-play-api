package components

import com.softwaremill.macwire.wire
import persistence.entities.Car
import persistence.{InMemoryPersistence, Persistence}
import repositories.CarRepository

import scala.collection.mutable.ListBuffer

trait RepositoryComponents {
  lazy val persistence: Persistence[Car, Long, ListBuffer] = InMemoryPersistence
  lazy val carRepository: CarRepository = wire[CarRepository]
}
