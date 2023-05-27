package service

import db.UserDB
import models.UserType.{Admin, Customer}
import models.Users
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class UserRepoUnitTest extends AnyFunSuite with MockitoSugar {
  val userDB: UserDB = mock[UserDB]
  val userRepo = new UserRepo(userDB)

  test("Add user should return 'Data inserted successfully!'") {
    val user = Users("Jeet", 23, "Gorakhpur", "1998-10-12", Customer)
    when(userDB.addUser(user)).thenReturn(Future("Data inserted successfully!"))
    userRepo.addUser(user).onComplete {
      case Success(value) => assert(value == "Data inserted successfully!")
      case Failure(_) => false
    }
  }

  test("Add user should also return 'Data inserted successfully!'") {
    val user = Users("Bhavya", 30, "Delhi", "1997-04-04", Admin)
    when(userDB.addUser(user)).thenReturn(Future("Data inserted successfully!"))
    userRepo.addUser(user).onComplete {
      case Success(value) => assert(value == "Data inserted successfully!")
      case Failure(_) => false
    }
  }

  test("Get user by ID should return an user's detail in list") {
    val userId = 45
    val user = List(Users("Bhavya", 30, "Delhi", "1997-04-04", Admin, userId))
    when(userDB.getById(userId)).thenReturn(Future(user))
    userRepo.getById(userId).onComplete {
      case Success(value) => assert(value.contains(user))
      case Failure(_) => false
    }
  }

  test("Get all users should return a list of all users in a list") {
    val adminId = 45
    val customerId = 46
    val users = List(
      Users("Jeet", 23, "Gorakhpur", "1998-10-12", Customer, customerId),
      Users("Bhavya", 30, "Delhi", "1997-04-04", Admin, adminId)
    )
    when(userDB.getAll).thenReturn(Future(users))
    userRepo.getAll.onComplete {
      case Success(value) => assert(value == users)
      case Failure(_) => false
    }
  }

  test("Update user by ID should return 'Value updated successfully!'") {
    val userId = 45
    val valueToUpdate = "Bhavya Verma"
    when(userDB.updateById(userId, valueToUpdate)).thenReturn(Future("Value updated successfully!"))
    userRepo.updateById(userId, valueToUpdate).onComplete {
      case Success(value) => assert(value == "Value updated successfully!")
      case Failure(_) => false
    }
  }

  test("delete user by ID should return 'User Deleted!'") {
    val userId = 46
    when(userDB.deleteById(userId)).thenReturn(Future("User Deleted!"))
    userRepo.deleteById(userId).onComplete {
      case Success(value) => assert(value == "User Deleted!")
      case Failure(_) => false
    }
  }

  test("delete all should return 'All users deleted!'") {
    when(userDB.deleteAll()).thenReturn(Future("All users deleted!"))
    userRepo.deleteAll().onComplete {
      case Success(value) => assert(value == "All users deleted!")
      case Failure(_) => false
    }
  }
}