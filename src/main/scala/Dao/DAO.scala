package Dao

import java.util.UUID
import scala.collection.mutable.ListBuffer
import models.Users

trait DAO {

  def addUser(user: Users): String

  def getById(userId: UUID): Option[Users]

  def getAll: ListBuffer[Users]

  def updateById(userId: UUID, valueToUpdate: String): ListBuffer[Users]

  def deleteById(userID: UUID): ListBuffer[Users]

  def deleteAll(): String
}
