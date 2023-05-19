package db

import models.UserType.{Admin, Customer}
import models.Users
import org.scalatest.flatspec.AnyFlatSpec

import java.sql.Date
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class UserDBUnitTest extends AnyFlatSpec {

  val userDB = new UserDB

  "addUser" should "Add user should return 'Data inserted successfully!'" in {
    val user = Users("Ram", 23, "Kolkata", "1998-01-01", Customer)
    userDB.addUser(user).andThen {
      case Success(value) => assert(value == "Data inserted successfully!")
      case Failure(_) => false
    }
  }

  it should "Get user by ID should return an Option[Users]" in {
    val userId = 77
    val expectedResult = List(77, "Ram", 23, "Kolkata", Date.valueOf("1998-01-01"), "Customer")
    userDB.getById(userId).andThen {
      case Success(value) => assert(value == List(expectedResult))
      case Failure(_) => false
    }
  }

  it should "Get all users should return a List[List[Users]]" in {
    val users = List(
      List(45, "Bhavya Verma", 30, "Delhi", Date.valueOf("1997-04-04"), "Admin"),
      List(48, "Jitendra", 23, "Noida", Date.valueOf("1998-10-12"), "Customer"),
      List(74, "Pawan", 19, "Kanpur", Date.valueOf("2005-03-02"), "Customer"),
      List(75, "Akhil", 24, "Bihar", Date.valueOf("1999-02-03"), "Customer"),
      List(77, "Ram Kumar", 23, "Kolkata", Date.valueOf("1998-01-01"), "Customer")
    )
    userDB.getAll.andThen {
      case Success(value) => assert(value == users)
      case Failure(_) => false
    }
  }

  it should "Update user by ID should return a 'Value updated successfully!'" in {
    val userID = 77
    val newName = "Ram Kumar"
    userDB.updateById(userID, newName).andThen {
      case Success(value) => assert(value == "Value updated successfully!")
      case Failure(_) => false
    }
  }

  it should "Delete user by ID should return 'User Deleted!'" in {
    val userId = 78
    userDB.deleteById(userId).andThen {
      case Success(value) => assert(value == "User Deleted!")
      case Failure(_) => false
    }
  }

  it should "Delete all users should return 'All users deleted!'" in {
    userDB.deleteAll().andThen {
      case Success(value) => assert(value == "All users deleted!")
      case Failure(_) => false
    }
  }
}
