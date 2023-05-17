package service

import Dao.DAO
import models.Users

import java.sql.{ResultSet, SQLException}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class UserRepo(userDB: DAO) {

  def addUser(user: Users): Future[String] = Future {
    userDB.addUser(user)

    "Data inserted successfully!"
  }

  def getById(userId: Int): Future[ResultSet] = {
    Try(userDB.getById(userId)) match {
      case Success(resultSet) => resultSet
      case Failure(_) => throw new SQLException
    }
  }

  def getAll: Future[ResultSet] = {
    Try(userDB.getAll) match {
      case Success(resultSet) => resultSet
      case Failure(_) => throw new SQLException
    }
  }

  def updateById(userId: Int, valueToUpdate: String): Future[String] = Future {
    userDB.updateById(userId, valueToUpdate)
    "Value updated successfully!"
  }

  def deleteById(userID: Int): Future[String] = Future {
    userDB.deleteById(userID)
    "User Deleted!"
  }

  def deleteAll(): Future[String] = {
    userDB.deleteAll()
  }
}
