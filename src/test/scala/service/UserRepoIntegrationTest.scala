package service

import db.UserDB
import models.UserType.{Admin, Customer}
import models.Users
import org.scalatest.flatspec.AnyFlatSpec
import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class UserRepoIntegrationTest extends AnyFlatSpec {

  val userDB = new UserDB
  val userRepo = new UserRepo(userDB)
  val customerUserId: UUID = UUID.randomUUID()
  val adminUserId: UUID = UUID.randomUUID()

  "addUser" should "return 'new user added.'" in {
    val user = Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer)
    val actualResult = userRepo.addUser(user)
    val expectedResult = "new user added."
    actualResult.onComplete {
      case Success(value) => assert(value == expectedResult)
      case Failure(_) => false
    }
  }

  it should "also return new user added." in {
    val user = Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    val actualResult = userRepo.addUser(user)
    actualResult.onComplete {
      case Success(value) => assert(value == "new user added.")
      case Failure(_) => false
    }
  }

  it should "also return an Option[Users]'" in {
    val expectedResult = Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    val actualResult = userRepo.getById(adminUserId)
    actualResult.onComplete {
      case Success(value) => assert(value.contains(expectedResult))
      case Failure(_) => false
    }
  }

  it should "return all values of ListBuffer[Users]'" in {
    val expectedResult = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    )

    val actualResult = userRepo.getAll
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == expectedResult)
        case Failure(_) => false
      }
    }
  }

  it should "return an updated ListBuffer[Users]'" in {
    val expectedResult = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya Verma", 26, "Delhi", "12/2/1996", Admin)
    )
    val actualResult = userRepo.updateById(adminUserId, "Bhavya Verma")
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == expectedResult)
        case Failure(_) => false
      }
    }
  }

  it should "return an ListBuffer[Users] after deletion by Id" in {
    val expectedResult = ListBuffer(
      Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    )
    val actualResult = userRepo.deleteById(customerUserId)
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == expectedResult)
        case Failure(_) => false
      }
    }
  }

  it should "return an empty ListBuffer[Users]'" in {
    val expectedResult = "All Deleted!"
    val actualResult = userRepo.deleteAll()
    actualResult match {
      case result => result.onComplete {
        case Success(value) => assert(value == expectedResult)
        case Failure(_) => false
      }
    }
  }
}