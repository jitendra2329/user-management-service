package db

import models.UserType.{Admin, Customer}
import models.Users
import org.scalatest.flatspec.AnyFlatSpec
import scala.util.{Success, Failure}
import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global

class UserDBUnitTest extends AnyFlatSpec {

  val userDB = new UserDB
  val customerUserId: UUID = UUID.randomUUID()
  val adminUserId: UUID = UUID.randomUUID()

  "addUser" should "Add user should return new user added." in {
    val user = Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer)
    userDB.addUser(user).onComplete {
      case Success(value) => assert(value == "new user added.")
      case Failure(_) => "Error"
    }
  }

  it should "Add user and should return new user added." in {
    val user = Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    userDB.addUser(user).onComplete {
      case Success(value) => assert(value == "new user added.")
      case Failure(_) => "Error"
    }
  }

  it should "Get user by ID should return an Option[Users]" in {
    val user = Some(Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin))
    userDB.getById(adminUserId).onComplete {
      case Success(value) => assert(value == user)
      case Failure(_) => "Error"
    }
  }

  it should "Get all users should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    userDB.getAll.onComplete {
      case Success(value) => assert(value == users)
      case Failure(_) => "Error"
    }
  }

  it should "Update user by ID should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya Verma", 24, "Delhi", "12/2/1998", Admin)
    )
    val newName = "Bhavya Verma"
    userDB.updateById(adminUserId, newName).onComplete {
      case Success(value) => assert(value == users)
      case Failure(_) => "Error"
    }
  }

  it should "Delete user by ID should return a ListBuffer[Users]" in {
    val users = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya", 24, "Delhi", "12/2/1998", Admin)
    )
    userDB.deleteById(customerUserId).onComplete {
      case Success(value) => assert(value == users.filterNot(_.userId == customerUserId))
      case Failure(_) => "Error"
    }
  }

  it should "Delete all users should return 'All Deleted!'" in {
    userDB.deleteAll().onComplete {
      case Success(value) => assert(value == "All Deleted!")
      case Failure(_) => "Error"
    }
  }
}