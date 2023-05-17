package Dao

import models.Users

import java.sql.ResultSet
import scala.concurrent.Future

trait DAO {

  def addUser(user: Users): Future[String]

  def getById(userId: Int): Future[ResultSet]

  def getAll: Future[ResultSet]

  def updateById(userId: Int, valueToUpdate: String): Future[String]

  def deleteById(userID: Int): Future[String]

  def deleteAll(): Future[String]
}
