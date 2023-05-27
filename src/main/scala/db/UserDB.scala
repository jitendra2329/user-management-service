package db

import Dao.DAO
import models.UserType.{Admin, Customer}
import models.Users

import scala.util.{Failure, Success, Try}
import java.sql.{Date, ResultSet, SQLException, Statement}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserDB extends DAO {

  private val statement: Statement = Connection.connection().createStatement()

  def addUser(user: Users): Future[String] = Future {
    val query = s"INSERT INTO userTable (userName, age, address, dateOfBirth, userType) VALUES ('${user.userName}', ${user.age}, '${user.address}', '${user.dateOfBirth}', '${user.userType}') ;"
    Try(statement.executeQuery(query))
    "Data inserted successfully!"
  }

  def getById(userId: Int): Future[Option[Users]] = Future {
    Thread.sleep(150)
    val query = s"SELECT  * FROM userTable WHERE userId = $userId ;"
    queryExecution(query) match {
      case List(value) => Some(value)
      case _ => None
    }
  }

  def getAll: Future[List[Users]] = Future {
    val query = "SELECT * FROM userTable ORDER BY userId ASC;"
    Thread.sleep(150)
    queryExecution(query)
  }

  private def queryExecution(query: String): List[Users] = {
    Try(statement.executeQuery(query)) match {
      case Success(resultSet) => resultSetToList(resultSet)
      case Failure(_) => throw new SQLException
    }
  }

  private val listBuffer = ListBuffer[Users]()

  @tailrec
  private def resultSetToList(resultSet: ResultSet): List[Users] = {
    if (resultSet.next()) {
      val userId = resultSet.getInt("userID")
      val userName = resultSet.getString("userName")
      val age = resultSet.getInt("age")
      val address = resultSet.getString("address")
      val dob = resultSet.getDate("dateOfBirth")
      val userType = resultSet.getString("userType") match {
        case "Admin" => Admin
        case "Customer" => Customer
      }
      listBuffer += Users(userName, age, address, dob.toString, userType, userId)
      resultSetToList(resultSet)
    } else listBuffer.toList
  }

  def updateById(userId: Int, valueToUpdate: String): Future[String] = Future {
    val query = s"UPDATE userTable SET userName = '$valueToUpdate' WHERE userId = $userId;"
    statement.executeQuery(query)
    "Value updated successfully!"
  }

  def deleteById(userID: Int): Future[String] = {
    val query = s"DELETE FROM userTable WHERE userId = $userID;"
    queryExecutor(query)
  }

  def deleteAll(): Future[String] = {
    val query = "TRUNCATE userTable;"
    queryExecutor(query)
  }

  private def queryExecutor(query: String): Future[String] = {
    Try(statement.executeQuery(query))
    getAll.map { value =>
      if (value.isEmpty) "All users deleted!"
      else "User Deleted!"
    }
  }
}
