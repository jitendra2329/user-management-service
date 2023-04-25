package models

sealed trait UserType

object UserType extends UserType {
  case object Customer extends UserType

  case object Admin extends UserType
}
