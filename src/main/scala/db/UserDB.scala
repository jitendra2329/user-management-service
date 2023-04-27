package db

import Dao.DAO
import models.Users
import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserDB extends DAO {

  private var userStorage: ListBuffer[Users] = ListBuffer.empty

  def addUser(user: Users): Future[String] = Future {
    userStorage += user
    Thread.sleep(150)
    "new user added."
  }

  def getById(userId: UUID): Future[Option[Users]] = Future {
    Thread.sleep(150)
    userStorage.find(_.userId == userId)
  }

  def getAll: Future[ListBuffer[Users]] = Future {
    Thread.sleep(150)
    userStorage
  }

  def updateById(userId: UUID, valueToUpdate: String): Future[ListBuffer[Users]] = Future {
    userStorage.map {
      case users if users.userId == userId => users.copy(userName = valueToUpdate)
      case users => users
    }
  }

  def deleteById(userID: UUID): Future[ListBuffer[Users]] = Future {
    userStorage = userStorage.filterNot(_.userId == userID)
    Thread.sleep(150)
    userStorage
  }

  def deleteAll(): Future[String] = Future {
    userStorage.clear()
    Thread.sleep(150)
    "All Deleted!"
  }
}
