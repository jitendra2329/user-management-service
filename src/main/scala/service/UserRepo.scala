package service

import Dao.DAO
import models.Users

import java.util.UUID
import scala.concurrent.Await
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.DurationInt

class UserRepo(userDB: DAO) {

  def addUser(user: Users): String = {
    Await.result(userDB.addUser(user), 2.second)
  }

  def getById(userId: UUID): Option[Users] = {
    Await.result(userDB.getById(userId), 1.second)
  }

  def getAll: ListBuffer[Users] = {
    Await.result(userDB.getAll, 1.second)
  }

  def updateById(userId: UUID, valueToUpdate: String): ListBuffer[Users] = {
    Await.result(userDB.updateById(userId, valueToUpdate), 1.second)
  }

  def deleteById(userID: UUID): ListBuffer[Users] = {
    Await.result(userDB.deleteById(userID), 1.second)
  }

  def deleteAll(): String = {
    Await.result(userDB.deleteAll(), 1.second)
  }
}
