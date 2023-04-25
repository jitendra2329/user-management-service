package service

import Dao.DAO
import models.Users
import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserRepo(userDB: DAO) {

  def addUser(user: Users): String = userDB.addUser(user)

  def getById(userId: UUID): Option[Users] = userDB.getById(userId)

  def getAll: ListBuffer[Users] = userDB.getAll

  def updateById(userId: UUID, valueToUpdate: String): ListBuffer[Users] = userDB.updateById(userId, valueToUpdate)

  def deleteById(userID: UUID): ListBuffer[Users] = userDB.deleteById(userID)

  def deleteAll(): String = userDB.deleteAll()
}
