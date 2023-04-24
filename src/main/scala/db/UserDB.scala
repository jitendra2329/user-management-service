package db

//import Dao.DAO
import models.Users

import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserDB {

  private var userStorage: ListBuffer[Users] = ListBuffer.empty

  def addUser(user: Users): String = {
    userStorage += user
    "new user added."
  }

  def getById(userId: UUID): Option[Users] = {
    userStorage.find(_.userId == userId)
  }

  def getAll: ListBuffer[Users] = userStorage

  def updateById(userId: UUID, valueToUpdate: String): ListBuffer[Users] = {
    userStorage.map {
      case users if users.userId == userId => users.copy(userName = valueToUpdate)
      case users => users
    }
  }

  def deleteById(userID: UUID): ListBuffer[Users] = {
    userStorage = userStorage.filterNot(_.userId == userID)
    userStorage
  }

  def deleteAll(): String = {
    userStorage = ListBuffer.empty
    "All Deleted!"
  }
}
