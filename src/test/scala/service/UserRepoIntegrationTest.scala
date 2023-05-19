package service

import db.UserDB
import models.UserType.{Admin, Customer}
import models.Users
import org.scalatest.flatspec.AnyFlatSpec

import java.sql.Date
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class UserRepoIntegrationTest extends AnyFlatSpec {
  val userDB = new UserDB
  val userRepo = new UserRepo(userDB)

  "addUser" should "return 'Data inserted successfully!'" in {
    val user = Users("Pawan", 19, "Kanpur", "2005-03-02", Customer)
    val actualResult = userRepo.addUser(user)
    val expectedResult = "Data inserted successfully!"
    actualResult.onComplete {
      case Success(value) => assert(value == expectedResult)
      case Failure(_) => false
    }
  }

  it should "also return 'Data inserted successfully!'" in {
    val user = Users("Akhil", 24, "Bihar", "1999-02-03", Customer)
    val actualResult = userRepo.addUser(user)
    actualResult.onComplete {
      case Success(value) => assert(value == "Data inserted successfully!")
      case Failure(_) => false
    }
  }

  it should "return a user in list" in {
    val userId = 74
    val expectedResult = List(userId, "Pawan", 19, "Kanpur", Date.valueOf("2005-03-02"), "Customer")
    val actualResult = userRepo.getById(userId)
    actualResult.onComplete {
      case Success(value) => assert(value == List(expectedResult))
      case Failure(_) => false
    }
  }

  it should "return details of all users in List" in {
    val expectedResult = List(
      List(45, "Bhavya Verma", 30, "Delhi", Date.valueOf("1997-04-04"), "Admin"),
      List(48, "Jitendra", 23, "Noida", Date.valueOf("1998-10-12"), "Customer"),
      List(74, "Pawan", 19, "Kanpur", Date.valueOf("2005-03-02"), "Customer"),
      List(75, "Akhil", 24, "Bihar", Date.valueOf("1999-02-03"), "Customer")
    )

    val actualResult = userRepo.getAll
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == expectedResult)
        case Failure(_) => false
      }
    }
  }

  it should "return an updated 'Value updated successfully!'" in {
    val userId = 45
    val actualResult = userRepo.updateById(userId, "Bhavya Verma")
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == "Value updated successfully!")
        case Failure(_) => false
      }
    }
  }

  it should "return 'User Deleted!' after deletion by Id" in {
    val userId = 75
    val actualResult = userRepo.deleteById(userId)
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == "User Deleted!")
        case Failure(_) => false
      }
    }
  }

  it should "return an empty All users deleted!'" in {
    val expectedResult = "All users deleted!"
    val actualResult = userRepo.deleteAll()
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == expectedResult)
        case Failure(_) => false
      }
    }
  }
}