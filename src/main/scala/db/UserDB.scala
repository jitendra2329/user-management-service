package db

import models.Users

import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserDB {

  private var UserStorage: ListBuffer[Users] = ListBuffer.empty

  def addUser(user: Users): Boolean = {
    UserStorage += user
    true
  }

  def getById(userId: UUID): Option[Users] = {
    UserStorage.find(_.userId == userId)
  }

  def getAll: ListBuffer[Users] = UserStorage

  def updateById(userId: UUID, valueToUpdate: String): ListBuffer[Users] = {
    UserStorage.map {
      case users if users.userId == userId => users.copy(userName = valueToUpdate)
      case users => users
    }
  }

  def deleteById(userID: UUID): ListBuffer[Users] = {
    UserStorage = UserStorage.filterNot(_.userId == userID)
    UserStorage
  }

  def deleteAll(): String = {
    UserStorage = ListBuffer.empty
    "All Deleted!"
  }
}
