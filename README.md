# User API

## Overview

This project is a Spring Boot-based RESTful API for managing user data, including signup, retrieval, update, and deletion of user details. The application also integrates with an Excel file to store and retrieve user data.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
  - [Running the Application](#running-the-application)
  - [API Endpoints](#api-endpoints)
- [Excel File Handling](#excel-file-handling)
- [Swagger API Documentation](#swagger-api-documentation)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- **Java** (JDK 17)
- **Spring Boot** 3.x.x
- **Spring Data JPA**
- **MySQL** (Database)
- **Apache POI** (Excel file handling)
- **Swagger/OpenAPI** (API Documentation)
- **Lombok** (Boilerplate code reduction)
- **JUnit 5** (Testing)
- **Maven** (Dependency management)

## Project Structure
  ```bash
```plaintext
User API
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.userapi
│   │   │       ├── controller
│   │   │       │   └── UserController.java        # Handles API endpoints for user management
│   │   │       ├── model
│   │   │       │   ├── User.java                  # User entity with validation and schema documentation
│   │   │       │   └── Address.java               # Embedded Address entity within User
│   │   │       ├── repository
│   │   │       │   └── UserRepository.java        # JPA repository interface for User
│   │   │       ├── service
│   │   │       │   └── UserService.java           # Service layer with business logic for user operations
│   │   │       └── util
│   │   │           └── ExcelFileHandler.java      # Utility class for handling Excel file operations
│   │   ├── resources
│   │   │   ├── application.properties             # Application configuration properties
│   │   │   └── userdata.xlsx                      # Excel file to store user data
│   └── test
│       └── java
│           └── com.example.userapi
│               └── UserApiApplicationTests.java   # Unit tests for the application
├── .gitignore                                      # Git ignore file
├── pom.xml                                         # Maven configuration file
└── README.md                                       # Project documentation
 ```
## Features

- **User Signup:** Allows users to sign up with their details.
- **Retrieve User Details:** Get user details by role or fetch all users.
- **Update User:** Modify user information by ID.
- **Delete User:** Remove user details by ID.
- **Excel Integration:** Store and manage user details in an Excel file.
- **Swagger Integration:** API documentation and testing through Swagger UI.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/user-api.git
   cd user-api
   
2. Update the MySQL database credentials:
Update src/main/resources/application.properties with your MySQL database username and password:
 ```bash
spring.datasource.username=your_username
spring.datasource.password=your_password
 ```
4. Build the project using Maven:
 ```bash   
mvn clean install
 ```
▶️USAGE

➡️Running the Application
You can run the application using the following command:
```bash 
mvn spring-boot:run
```
The application will start on http://localhost:8080.

➡️API Endpoints:-
POST /api/users/signup: Create a new user.
GET /api/users/admin: Retrieve details of users with the "admin" role.
GET /api/users/user: Retrieve details of users with the "user" role.
GET /api/users/data: Retrieve details of all users.
PUT /api/users/{id}: Update user details by ID.
DELETE /api/users/{id}: Delete user details by ID.
Excel File Handling
User data is stored in an Excel file (userdata.xlsx) located in src/main/resources/. The ExcelFileHandler class handles the reading and writing operations for this file.

Every time a user is added, updated, or deleted, the Excel file is updated accordingly.

➡️Swagger API Documentation:-
This project uses Swagger for API documentation. Once the application is running, you can access the Swagger UI at:
```bash 
http://localhost:8080/swagger-ui
```
Here, you can interact with the API endpoints and view their details.

➡️Testing:-
The project includes unit tests for the service layer. To run the tests:
```bash 
mvn test
```

➡️License:-
This project is licensed under the MIT License. See the LICENSE file for details.
```bash 
Replace `yourusername` with your actual GitHub username in the clone URL, and feel free to adjust the content based on any specific details or requirements for your project.
```
