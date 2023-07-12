package components
import com.softwaremill.macwire._
import managers.CarManager

trait ManagerComponents {
  lazy val carManager: CarManager = wire[CarManager]
}
