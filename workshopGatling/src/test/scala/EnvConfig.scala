import com.typesafe.config._

/**
  * Created by rocky
  */
object EnvConfig {
  val env = System.getProperty("mainEnv", "test")
  println("env: " + env)

  val conf = ConfigFactory.load(env)
  def apply() = conf.getConfig("conf")
}