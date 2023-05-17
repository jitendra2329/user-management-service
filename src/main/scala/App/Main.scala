package App

import db.UserDB
import models.UserType.{Admin, Customer}
import models.{UserType, Users}
import service.UserRepo

import java.sql.ResultSet
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Main extends App {

  private val userDB = new UserDB

  private val userRepo = new UserRepo(userDB)

  println("Enter your choice : ")
  println("1. Add user\n2. Get user detail by Id\n3. Get all users details\n4. Update user detail by Id\n5. Delete user by Id\n6. Delete all\n")

  private val choice = Try(StdIn.readInt())
  choice match {
    case Success(choice) => choice match {
      case 1 => addUsers()
      case 2 => getUserDetailById()
      case 3 => getAllUsers()
      case 4 => updateUserDetailById()
      case 5 => deleteUserById()
      case 6 => deleteAllUsers()
      case _ => println("Invalid choice!")
    }
    case Failure(_) => println("Invalid Choice!")
  }

  private def addUsers(): Unit = {
    println("Enter name :")
    val name = StdIn.readLine()
    println("Enter age :")
    val age = StdIn.readInt()
    println("Enter address :")
    val address = StdIn.readLine()
    println("Enter a date (YYYY-MM-DD): ")
    val dob = StdIn.readLine()
    println("Enter usertype :")
    val userType = StdIn.readLine()

    val typeOfUser: Either[Unit, UserType] = userType match {
      case "Admin" => Right(Admin)
      case "Customer" => Right(Customer)
      case _ => Left("Invalid user")
    }


    val user = Try(Users(name, age, address, dob, typeOfUser.getOrElse(throw new RuntimeException)))
    user match {
      case Success(value) => userRepo.addUser(value).onComplete {
        case Success(value) => println(value)
        case Failure(exception) => println(exception.getMessage)
      }
      case Failure(_) => println("Invalid user type!!")
    }

  }

  private def getUserDetailById(): Unit = {
    println("Enter Id: ")
    val id = StdIn.readInt()
    userRepo.getById(id).onComplete {
      case Success(resultSet) => display(resultSet)
      case Failure(exception) => println(exception.getMessage)
    }

    //    @tailrec

  }

  @tailrec
  def display(resultSet: ResultSet): Unit = {
    if (resultSet.next()) {
      val id = resultSet.getInt("userId")
      val name = resultSet.getString("userName")
      val age = resultSet.getInt("age")
      val address = resultSet.getString("address")
      val dob = resultSet.getDate("dateOfBirth")
      val userType = resultSet.getString("userType")

      println(s"Id: $id,  Name: $name, age: $age, address: $address, dob: $dob, userType: $userType")
    }
    display(resultSet)
  }


  private def getAllUsers(): Unit = {
    val listOfUser = userRepo.getAll
    listOfUser.onComplete {
      case Success(resultSet) => display(resultSet)
      case Failure(exception) => println(exception.getMessage)
    }
  }

  private def updateUserDetailById(): Unit = {
    println("Enter Id: ")
    val id = StdIn.readInt()
    println("Enter name to update : ")
    val name = StdIn.readLine()

    userRepo.updateById(id, name).onComplete {
      case Success(value) => println(value)
      case Failure(exception) => println(exception.getMessage)
    }
  }

  private def deleteUserById(): Unit = {
    println("Enter Id: ")
    val id = StdIn.readInt()
    userRepo.deleteById(id).onComplete {
      case Success(v) => println(v)
      case Failure(exception) => println(exception.getMessage)
    }
  }

  private def deleteAllUsers(): Unit = {
    userRepo.deleteAll().onComplete {
      case Success(_) => println("Values Deleted!")
      case Failure(exception) => println(exception.getMessage)
    }
  }

  Thread.sleep(2000)
}

