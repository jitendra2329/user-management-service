package Dao

import models.Users

import scala.concurrent.Future

trait DAO {

  def addUser(user: Users): Future[String]

  def getById(userId: Int): Future[List[Any]]

  def getAll: Future[List[Any]]

  def updateById(userId: Int, valueToUpdate: String): Future[String]

  def deleteById(userID: Int): Future[String]

  def deleteAll(): Future[String]
}
