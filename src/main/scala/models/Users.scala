package models

import java.util.UUID

case class Users(
    userId: UUID,
    userName: String,
    age: Int,
    address: String,
    dateOfBirth: String,
    userType: UserType
  )
