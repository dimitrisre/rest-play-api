import controllers.ConsoleController
import managers.CarManager
import persistence.InMemoryPersistence
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import repositories.CarRepository

class ConsoleApplicationLoader() extends ApplicationLoader {
  override def load(context: ApplicationLoader.Context): Application = {
    new ConsoleApiComponents(context).application
  }

}

class ConsoleApiComponents(context: ApplicationLoader.Context)
  extends BuiltInComponentsFromContext(context) {
  lazy val carRepository: CarRepository = new CarRepository(InMemoryPersistence)
  lazy val carManager: CarManager = new CarManager(carRepository)
  lazy val consoleController:ConsoleController =  new ConsoleController(carManager)

  applicationLifecycle.addStopHook(() => consoleController.stop())
  consoleController.start()

  override def router: Router = Router.empty

  override def httpFilters: Seq[EssentialFilter] = Seq.empty

}