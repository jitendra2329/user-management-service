package service

import Dao.DAO
import models.Users

import java.sql.SQLException
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class UserRepo(userDB: DAO) {

  def addUser(user: Users): Future[String] = Future {
    userDB.addUser(user)
    "Data inserted successfully!"
  }

  def getById(userId: Int): Future[Option[Users]] = {
//    getResult(Try(userDB.getById(userId)))
    Try(userDB.getById(userId)) match {
      case Success(user) => user
      case Failure(_) => throw new RuntimeException
    }
  }

  private def getResult(result: Try[Future[List[Users]]]): Future[List[Users]] = {
    result match {
      case Success(result) => result
      case Failure(_) => throw new SQLException
    }
  }

  def getAll: Future[List[Users]] = {
    getResult(Try(userDB.getAll))
  }

  def updateById(userId: Int, valueToUpdate: String): Future[String] = Future {
    userDB.updateById(userId, valueToUpdate)
    "Value updated successfully!"
  }

  def deleteById(userID: Int): Future[String] = Future {
    userDB.deleteById(userID)
    "User Deleted!"
  }

  def deleteAll(): Future[String] = Future {
    userDB.deleteAll()
    "All users deleted!"
  }
}
