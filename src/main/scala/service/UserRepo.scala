package service

import Dao.DAO
import models.Users
import java.util.UUID
import scala.concurrent.Future
import scala.collection.mutable.ListBuffer

class UserRepo(userDB: DAO) {

  def addUser(user: Users): Future[String] = {
    userDB.addUser(user)
  }

  def getById(userId: UUID): Future[ListBuffer[Users]] = {
    userDB.getById(userId)
  }

  def getAll: Future[ListBuffer[Users]] = {
    userDB.getAll
  }

  def updateById(userId: UUID, valueToUpdate: String): Future[ListBuffer[Users]] = {
    userDB.updateById(userId, valueToUpdate)
  }

  def deleteById(userID: UUID): Future[ListBuffer[Users]] = {
    userDB.deleteById(userID)
  }

  def deleteAll(): Future[String] = {
    userDB.deleteAll()
  }
}
