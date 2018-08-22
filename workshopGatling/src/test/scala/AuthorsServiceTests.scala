import io.gatling.core.Predef._

import io.gatling.http.Predef.http
import scala.concurrent.duration._
import scala.language.postfixOps


class AuthorsServiceTests extends Simulation {

  val httpConf = http.baseURL(EnvConfig().getString("env.serviceAuthorsUrl"))

  val scn = scenario("Authors").exec(
    AuthorsServiceRequests.authorsPerPage,
    AuthorsServiceRequests.authorByNameValid,
    AuthorsServiceRequests.authorByNameInvalid
  )

  setUp(scn.inject(constantUsersPerSec(
    EnvConfig().getInt("loading.usersCount")) during(
    EnvConfig().getInt("loading.duration") seconds))
    .protocols(httpConf))

}