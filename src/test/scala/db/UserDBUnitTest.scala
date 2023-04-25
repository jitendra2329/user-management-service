package db

import models.UserType.{Admin, Customer}
import models.Users
import org.scalatest.flatspec.AnyFlatSpec
import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserDBUnitTest extends AnyFlatSpec {

  val userDB = new UserDB
  val customerUserId: UUID = UUID.randomUUID()
  val adminUserId: UUID = UUID.randomUUID()

  it should "Add user should return new user added." in {
    val user = Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer)
    assert(userDB.addUser(user) == "new user added.")
  }

  it should "Add user and should return new user added." in {
    val user = Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    assert(userDB.addUser(user) == "new user added.")
  }

  it should "Get user by ID should return an Option[Users]" in {
    val user = Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    assert(userDB.getById(adminUserId).contains(user))
  }

  it should "Get all users should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    assert(userDB.getAll == users)
  }

  it should "Update user by ID should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya Verma", 24, "Delhi", "12/2/1998", Admin)
    )
    val newName = "Bhavya Verma"
    val actualUser = userDB.updateById(adminUserId, newName)
    assert(users == actualUser)
  }

  it should "Delete user by ID should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    assert(userDB.deleteById(customerUserId) == users.filterNot(_.userId == customerUserId))
  }

  it should "Delete all users should return 'All Deleted!'" in {
    assert(userDB.deleteAll() == "All Deleted!")
  }
}


