package App

import db.UserDB
import models.UserType.{Admin, Customer}
import service.UserRepo
import models.Users
import java.util.UUID

object Main extends App {

  private val userDB = new UserDB

  private val userRepo = new UserRepo(userDB)

  private val user1 = Users(UUID.randomUUID(), "jks", 23, "gkp", "12/2/1998", Customer)
  println(userRepo.addUser(user1))

  private val user2 = Users(UUID.randomUUID(), "Jeet", 24, "Delhi", "12/2/1998", Customer)
  println(userRepo.addUser(user2))

  private val user3 = Users(UUID.randomUUID(), "Ajit", 24, "Noida", "12/2/1998", Admin)
  println(userRepo.addUser(user3))

  private val listOfUser = userRepo.getAll
  println("-------- All users ---------")
  listOfUser.foreach(println)

  println("\n------- User by Id ----------")
  println(userRepo.getById(user2.userId))

  private val listAfterUpdation = userRepo.updateById(user3.userId, "Ajit Kumar")
  println("\n------- Users after updation --------")
  listAfterUpdation.foreach(println)

  println("\n-------- Users after deletion by Id -----------")
  println(userRepo.deleteById(user1.userId))

  println("\n" + userRepo.deleteAll())

  println("-------- All Users Deleted ---------------")
  println(userRepo.getAll)
}


