package db

import java.sql.{Connection, DriverManager, SQLException}
import scala.util.{Failure, Success, Try}

object Connection {

  private val url = "jdbc:postgresql://localhost:5432/users"
  private val username = "jitendra"
  private val password = "123456789"

  def connection(): Connection = {
    Try(DriverManager.getConnection(url, username, password)) match {
      case Success(conn) => conn
      case Failure(_) => throw new SQLException()
    }
  }
}
