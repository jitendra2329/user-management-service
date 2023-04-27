package App

import db.UserDB
import models.UserType.{Admin, Customer}
import service.UserRepo
import models.Users
import scala.util.{Success,Failure}

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  private val userDB = new UserDB

  private val userRepo = new UserRepo(userDB)

  private val user1 = Users(UUID.randomUUID(), "jks", 23, "gkp", "12/2/1998", Customer)
  userRepo.addUser(user1).onComplete{
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  private val user2 = Users(UUID.randomUUID(), "Jeet", 24, "Delhi", "12/2/1998", Customer)
  userRepo.addUser(user2).onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  private val user3 = Users(UUID.randomUUID(), "Ajit", 24, "Noida", "12/2/1998", Admin)
  userRepo.addUser(user3).onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  private val listOfUser = userRepo.getAll
  listOfUser.onComplete{
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  val result = userRepo.getById(user2.userId)
  result.onComplete{
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  private val listAfterUpdation = userRepo.updateById(user3.userId, "Ajit Kumar")
  listAfterUpdation.andThen {
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  userRepo.deleteById(user1.userId).andThen{
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  userRepo.deleteAll().onComplete{
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  userRepo.getAll.onComplete{
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

  Thread.sleep(2000)
}
