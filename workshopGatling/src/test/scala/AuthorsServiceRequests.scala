import io.gatling.core.Predef._
import io.gatling.http.Predef._
import Constants._

object AuthorsServiceRequests {

  val requestNamePrefix = EnvConfig.env + "-Authors-"

  //GET.
  val authorsPerPage = exec(http(requestNamePrefix + "Per-Page-Request")
    .get(API_V1_AUTHORS + "?per-page=50")
    .check(status.is(200))
    .check(headerRegex("content-type", JSON_CONTENT_TYPE))
  )

  var authorByNameValid = exec(http(requestNamePrefix + "Author-"+VALID_AUTHOR_NAME+"-Valid-Request")
      .get(API_V1_AUTHORS + "/nickname/" + VALID_AUTHOR_NAME)
      .check(status.is(200))
      .check(jsonPath("$..id").ofType[Int])
      .check(jsonPath("$..user_id").ofType[Int])
      .check(jsonPath("$..nickname").ofType[String])
      .check(headerRegex("content-type", JSON_CONTENT_TYPE))
    )

  val authorByNameInvalid = exec(http(requestNamePrefix + "Author-"+ INVALID_AUTHOR_NAME +"-Invalid-Request")
     .get(API_V1_AUTHORS + "/nickname/" + INVALID_AUTHOR_NAME)
     .check(status.is(404))
     .check(jsonPath("$..name").ofType[String])
    .check(jsonPath("$..message").ofType[String])
     .check(headerRegex("content-type", JSON_CONTENT_TYPE))
   )
}
