package repositories

import persistence.Persistence
import persistence.entities.Car
import repositories.errors.RepositoryError

import scala.collection.mutable.ListBuffer
import scala.util.Try

class CarRepository(val db: Persistence[Car, Long, ListBuffer]){
  def findById(id: Long): Either[RepositoryError, Option[Car]] = handleIfErrors(db.find(id))
  def save(car: Car): Either[RepositoryError, Car] = handleIfErrors(db.insert(car))
  def update(car: Car): Either[RepositoryError, Unit] = handleIfErrors(db.update(car))
  def delete(id: Long): Either[RepositoryError, Unit] = handleIfErrors(db.delete(id))
  def findAll(): Either[RepositoryError, List[Car]] = handleIfErrors(db.findAll())

  private def handleIfErrors[A](f: => A) =
    Try(f).fold(e => Left(RepositoryError(e.getMessage)), v => Right(v))

}
