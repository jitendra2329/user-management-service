package service

import db.UserDB
import models.UserType.{Admin, Customer}
import models.Users
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import java.util.UUID
import scala.collection.mutable.ListBuffer

class UserRepoIntegrationTest extends AnyFlatSpec {

  val userDB = new UserDB
  val userRepo = new UserRepo(userDB)
  val customerUserId: UUID = UUID.randomUUID()
  val adminUserId: UUID = UUID.randomUUID()

  "addUser" should "return 'new user added.'" in {
    val user = Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer)
    val actualResult = userRepo.addUser(user)
    val expectedResult = "new user added."
    actualResult shouldEqual expectedResult
  }

  it should "also return new user added." in {
    val user = Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    val actualResult = userRepo.addUser(user)
    val expectedResult = "new user added."
    actualResult shouldEqual expectedResult
  }

  it should "also return an Option[Users]'" in {
    val expectedResult = Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    val actualResult = userDB.getById(adminUserId)
    actualResult match {
      case Some(value) => value shouldEqual expectedResult
      case None => true
    }
  }

  it should "return all values of ListBuffer[Users]'" in {
    val expectedResult = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    )
    val actualResult = userDB.getAll
    actualResult match {
      case result if result.isEmpty => assert(result != expectedResult)
      case result => result shouldEqual expectedResult
    }
  }

  it should "return an updated ListBuffer[Users]'" in {
    val expectedResult = ListBuffer(
      Users(customerUserId, "Jeet", 23, "gkp", "12/2/1998", Customer),
      Users(adminUserId, "Bhavya Verma", 26, "Delhi", "12/2/1996", Admin)
    )
    val actualResult = userDB.updateById(adminUserId, "Bhavya Verma")
    actualResult match {
      case result if result.isEmpty => assert(result != expectedResult)
      case result => result shouldEqual expectedResult
    }
  }

  it should "return an ListBuffer[Users] after deletion by Id" in {
    val expectedResult = ListBuffer(
      Users(adminUserId, "Bhavya", 26, "Delhi", "12/2/1996", Admin)
    )
    val actualResult = userDB.deleteById(customerUserId)
    actualResult match {
      case result if result.isEmpty => assert(result != expectedResult)
      case result => result shouldEqual expectedResult
    }
  }

  it should "return an empty ListBuffer[Users]'" in {
    val expectedResult = "All Deleted!"
    val actualResult = userDB.deleteAll()
    actualResult shouldEqual expectedResult
  }
}
