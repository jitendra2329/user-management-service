package db

import Dao.DAO
import models.UserType.{Admin, Customer}
import models.Users
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.mockito.MockitoSugar.mock
import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserDBUnitTest extends AnyFlatSpec {

  val userDB: DAO = mock[DAO]

  it should "Add user should return new user added." in {
    val user = Users(UUID.randomUUID(), "Jeet", 23, "gkp", "12/2/1998", Customer)
    when(userDB.addUser(user)).thenReturn("new user added.")
    assert(userDB.addUser(user) == "new user added.")
  }

  it should "Add user and should return new user added." in {
    val user = Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    when(userDB.addUser(user)).thenReturn("new user added.")
    assert(userDB.addUser(user) == "new user added.")
  }

  it should "Get user by ID should return an Option[Users]" in {
    val userId = UUID.randomUUID()
    val user = Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    when(userDB.getById(userId)).thenReturn(Some(user))
    assert(userDB.getById(userId).contains(user))
  }

  it should "Get all users should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(UUID.randomUUID(), "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    when(userDB.getAll).thenReturn(users)
    assert(userDB.getAll == users)
  }

  it should "Update user by ID should return a ListBuffer[Users]" in {
    val userId = UUID.randomUUID()
    val users = ListBuffer(
      Users(userId, "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    val newName = "Bhavya Verma"
    when(userDB.updateById(userId, newName)).thenReturn(users.map(user =>
      if (user.userId == userId) user.copy(userName = newName) else user))
    assert(userDB.updateById(userId, newName) == users.map(user =>
      if (user.userId == userId) user.copy(userName = newName) else user))
  }

  it should "Delete user by ID should return a ListBuffer[Users]" in {
    val userId = UUID.randomUUID()
    val users = ListBuffer(
      Users(userId, "jks", 23, "gkp", "12/2/1998", Customer),
      Users(UUID.randomUUID(), "Bhavya Verma", 24, "Delhi", "12/2/1998", Admin)
    )
    when(userDB.deleteById(userId)).thenReturn(users.filterNot(_.userId == userId))
    assert(userDB.deleteById(userId) == users.filterNot(_.userId == userId))
  }

  it should "Delete all users should return 'All Deleted!'" in {
    when(userDB.deleteAll()).thenReturn("All Deleted!")
    assert(userDB.deleteAll() == "All Deleted!")
  }
}


