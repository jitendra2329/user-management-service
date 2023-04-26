package Dao

import java.util.UUID
import scala.collection.mutable.ListBuffer
import models.Users

import scala.concurrent.Future

trait DAO {

  def addUser(user: Users): Future[String]

  def getById(userId: UUID): Future[Option[Users]]

  def getAll: Future[ListBuffer[Users]]

  def updateById(userId: UUID, valueToUpdate: String): Future[ListBuffer[Users]]

  def deleteById(userID: UUID): Future[ListBuffer[Users]]

  def deleteAll(): Future[String]
}
