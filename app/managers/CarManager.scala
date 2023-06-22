package managers

import managers.errors.ManagerError
import models.CarDTO
import repositories.CarRepository
import models.util.CarConversions._
import repositories.errors.RepositoryError

trait Manager{
  def get(id: Long): Either[ManagerError, Option[CarDTO]]
  def save(car: CarDTO): Either[ManagerError, CarDTO]
  def update(car: CarDTO): Either[ManagerError, Unit]
  def delete(car: CarDTO): Either[ManagerError, Unit]
  def getAll(): Either[ManagerError, List[CarDTO]]
}

class CarManager(repository: CarRepository) extends Manager {
  override def get(id: Long): Either[ManagerError, Option[CarDTO]] =
    repository
      .findById(id)
      .fold(error => Left(toManagerError(error)), car => Right(car.map(_.toDTO())))



  override def save(carDTO: CarDTO): Either[ManagerError, CarDTO] =
    repository
      .save(carDTO.toDB())
      .fold(error => Left(toManagerError(error)), car => Right(car.toDTO()))


  override def update(carDTO: CarDTO): Either[ManagerError, Unit] =
    repository
      .update(carDTO.toDB())
      .fold(error => Left(toManagerError(error)), _ => Right(()))

  override def delete(carDTO: CarDTO): Either[ManagerError, Unit] =
    repository
      .delete(carDTO.id.getOrElse(0L))
      .fold(error => Left(toManagerError(error)), _ => Right(()))

  override def getAll(): Either[ManagerError, List[CarDTO]] =
    repository
      .findAll()
      .fold(error => Left(toManagerError(error)), carList => Right(carList.map(_.toDTO())))

  private def toManagerError(repositoryError: RepositoryError) = ManagerError(repositoryError.message)

}
