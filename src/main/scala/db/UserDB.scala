package db

import Dao.DAO
import models.Users

import scala.util.{Failure, Success, Try}
import java.sql.{ResultSet, SQLException, Statement}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserDB extends DAO {

  private val statement: Statement = Connection.connection().createStatement()

  def addUser(user: Users): Future[String] = Future {
    val query = s"INSERT INTO userTable (userName, age, address, dateOfBirth, userType) VALUES ('${user.userName}', ${user.age}, '${user.address}', '${user.dateOfBirth}', '${user.userType}') ;"

    Try(statement.executeQuery(query)) match {
      case Success(_) => "Data inserted successfully!"
      case Failure(exception) => exception.getMessage
    }
  }

  def getById(userId: Int): Future[ResultSet] = Future {
    Thread.sleep(150)
    val query = s"SELECT  * FROM userTable WHERE userId = $userId ;"
    Try(statement.executeQuery(query)) match {
      case Success(resultSet) => resultSet
      case Failure(_) => throw new SQLException
    }
  }

  def getAll: Future[ResultSet] = Future {
    val query = "SELECT * FROM userTable;"
    Thread.sleep(150)
    Try(statement.executeQuery(query)) match {
      case Success(resultSet) => resultSet
      case Failure(_) => throw new SQLException
    }
  }

  def updateById(userId: Int, valueToUpdate: String): Future[String] = Future {
    val query = s"UPDATE userTable SET userName = '$valueToUpdate' WHERE userId = $userId;"
    statement.executeQuery(query)
    "Value updated successfully!"
  }

  def deleteById(userID: Int): Future[String] = Future {
    val query = s"DELETE FROM userTable WHERE userId = $userID;"
    Try(statement.executeQuery(query)) match {
      case Success(resultSet) => ""
      case Failure(_) => throw new SQLException
    }
  }

  def deleteAll(): Future[String] = Future {
    val query = "TRUNCATE userTable;"
    Try(statement.executeQuery(query)) match {
      case Success(_) => "All Deleted!"
      case Failure(_) => throw new SQLException
    }
  }
}
