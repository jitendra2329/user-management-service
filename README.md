# User Management System

This project is a simple user management system implemented in Scala. It allows users to perform basic CRUD operations
on a list of users. The system consists of three main components: the database layer, the service layer, and the
application layer.

### Database Layer
The database layer is responsible for storing and retrieving data. In this project, a simple in-memory database has been
implemented using a ListBuffer data structure. This layer provides an interface for adding, updating, and deleting
users.

### Service Layer
The service layer acts as an intermediary between the database layer and the application layer. It provides an
abstraction layer that allows the application layer to interact with the database layer without having to deal with the
implementation details. In this project, the UserRepo class acts as the service layer.

### Application Layer
The application layer is responsible for interacting with the user and performing the necessary actions. In this
project, the Main object is responsible for creating users, updating user details, and deleting users.

### User Model
The Users model represents

The Users model represents a user in the system. It consists of the following fields:
    
    userId - a unique identifier for the user
    userName - the name of the user
    age - the age of the user
    address - the address of the user
    dateOfBirth - the date of birth of the user
    userType - the type of user (either Customer or Admin)
### Getting Started
#### Prerequisites
    Scala (version 2.13.6 or higher)
    sbt (version 1.5.5 or higher)

To run the project, follow these steps:

#### Clone the repository:
    git clone https://github.com/jitendra2329/user-management-service.git
#### Navigate to the project directory:
    cd user-management-service
#### Running:
    sbt run
#### Testing:
    sbt test
#### Code Coverage:
    sbt clean coverage test
    sbt coverageReport
#### Dependencies to add:
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
    libraryDependencies += "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test"
    libraryDependencies += "org.scalatest" %% "scalatest-wordspec" % "3.3.0-SNAP3" % Test
#### Plugins:
    addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")