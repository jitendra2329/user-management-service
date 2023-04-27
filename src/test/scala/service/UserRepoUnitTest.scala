package service

import db.UserDB
import models.UserType.{Admin, Customer}
import models.Users
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar
import scala.util.{Success, Failure}
import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserRepoUnitTest extends AnyFunSuite with MockitoSugar {

  val userDB: UserDB = mock[UserDB]
  val userRepo = new UserRepo(userDB)

  test("Add user should return 'new user added.'") {
    val user = Users(UUID.randomUUID(), "Jeet", 23, "gkp", "12/2/1998", Customer)
    when(userDB.addUser(user)).thenReturn(Future("new user added."))
    userRepo.addUser(user).onComplete {
      case Success(value) => assert(value == "new user added.")
      case Failure(_) => false
    }
  }

  test("Add user should return new user added.") {
    val user = Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    when(userDB.addUser(user)).thenReturn(Future("new user added."))
    userRepo.addUser(user).onComplete {
      case Success(value) => assert(value == "new user added.")
      case Failure(_) => false
    }
  }

  test("Get user by ID should return an Option[Users]") {
    val userId = UUID.randomUUID()
    val user = Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    when(userDB.getById(userId)).thenReturn(Future(ListBuffer(user)))
    userRepo.getById(userId).onComplete {
      case Success(value) => assert(value == ListBuffer(user))
      case Failure(_) => false
    }
  }

  test("Get all users should return a ListBuffer[Users]") {
    val users = ListBuffer(
      Users(UUID.randomUUID(), "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    when(userDB.getAll).thenReturn(Future(users))
    userRepo.getAll.onComplete {
      case Success(value) => assert(value == users)
      case Failure(_) => false
    }
  }

  test("Update user by ID should return a ListBuffer[Users]") {
    val userId = UUID.randomUUID()
    val users = ListBuffer(
      Users(userId, "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    val newName = "Bhavya Verma"
    when(userDB.updateById(userId, newName)).thenReturn(Future(users.map(user =>
      if (user.userId == userId) user.copy(userName = newName) else user)))
    userRepo.updateById(userId, newName).onComplete {
      case Success(value) => assert(value == users.map(user =>
        if (user.userId == userId) user.copy(userName = newName) else user))
      case Failure(_) => false
    }
  }

  test("Delete user by ID should return a ListBuffer[Users]") {
    val userId = UUID.randomUUID()
    val users = ListBuffer(
      Users(userId, "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya Verma", 24, "Delhi", "12/2/1998", Admin)
    )
    when(userDB.deleteById(userId)).thenReturn(Future(users.filterNot(_.userId == userId)))
    userRepo.deleteById(userId).onComplete {
      case Success(value) => assert(value == users)
      case Failure(_) => false
    }
  }

  test("Delete all users should return 'All Deleted!'") {
    when(userDB.deleteAll()).thenReturn(Future("All Deleted!"))
    userRepo.deleteAll().onComplete {
      case Success(value) => assert(value == "All Deleted!")
      case Failure(_) => false
    }
  }
}