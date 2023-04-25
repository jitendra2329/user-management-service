package service

import db.UserDB
import models.UserType.{Admin, Customer}
import models.Users
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar
import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserRepoUnitTest extends AnyFunSuite with MockitoSugar {

  val userDB: UserDB = mock[UserDB]
  val userRepo = new UserRepo(userDB)

  test("Add user should return 'new user added.'") {
    val user = Users(UUID.randomUUID(), "Jeet", 23, "gkp", "12/2/1998", Customer)
    when(userDB.addUser(user)).thenReturn("new user added.")
    assert(userRepo.addUser(user) == "new user added.")
  }

  test("Add user should return new user added.") {
    val user = Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    when(userDB.addUser(user)).thenReturn("new user added.")
    assert(userRepo.addUser(user) == "new user added.")
  }

  test("Get user by ID should return an Option[Users]") {
    val userId = UUID.randomUUID()
    val user = Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    when(userDB.getById(userId)).thenReturn(Some(user))
    assert(userRepo.getById(userId).contains(user))
  }

  test("Get all users should return a ListBuffer[Users]") {
    val users = ListBuffer(
      Users(UUID.randomUUID(), "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    when(userDB.getAll).thenReturn(users)
    assert(userRepo.getAll == users)
  }

  test("Update user by ID should return a ListBuffer[Users]") {
    val userId = UUID.randomUUID()
    val users = ListBuffer(
      Users(userId, "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    val newName = "Bhavya Verma"
    when(userDB.updateById(userId, newName)).thenReturn(users.map(user =>
      if (user.userId == userId) user.copy(userName = newName) else user))
    assert(userRepo.updateById(userId, newName) == users.map(user =>
      if (user.userId == userId) user.copy(userName = newName) else user))
  }

  test("Delete user by ID should return a ListBuffer[Users]") {
    val userId = UUID.randomUUID()
    val users = ListBuffer(
      Users(userId, "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya Verma", 24, "Delhi", "12/2/1998", Admin)
    )
    when(userDB.deleteById(userId)).thenReturn(users.filterNot(_.userId == userId))
    assert(userRepo.deleteById(userId) == users.filterNot(_.userId == userId))
  }

  test("Delete all users should return 'All Deleted!'") {
    when(userDB.deleteAll()).thenReturn("All Deleted!")
    assert(userRepo.deleteAll() == "All Deleted!")
  }
}
