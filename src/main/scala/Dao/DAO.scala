package Dao

import models.Users

import scala.concurrent.Future

trait DAO {

  def addUser(user: Users): Future[String]

  def getById(userId: Int):   Future[Option[Users]]

  def getAll:  Future[List[Users]]

  def updateById(userId: Int, valueToUpdate: String, fieldToUpdate: String): Future[String]

  def deleteById(userID: Int): Future[String]

  def deleteAll(): Future[String]
}
