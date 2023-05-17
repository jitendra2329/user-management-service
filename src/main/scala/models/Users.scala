package models

case class Users(
                  userName: String,
                  age: Int,
                  address: String,
                  dateOfBirth: String,
                  userType: UserType,
                  userId: Int = 0
                )
