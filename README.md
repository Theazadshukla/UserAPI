User API
Overview
The User API is a Spring Boot application for managing user information. It supports user sign-up, retrieval, updating, and deletion operations. The application integrates with a MySQL database and uses Apache POI to handle user data in an Excel file.

Features
User Sign-Up: Register new users with email and phone number validation.
User Retrieval: Fetch user details based on roles (admin or user), or get all user details.
User Update: Update existing user details.
User Deletion: Remove users from the database.
Excel File Integration: Store and update user information in an Excel file.
Technologies
Spring Boot: Framework for building the application.
Spring Data JPA: Simplifies database interactions using JPA.
MySQL: Relational database for storing user data.
Apache POI: Library for reading and writing Excel files.
Lombok: Reduces boilerplate code for Java objects.
Getting Started
Prerequisites
Java 22 or above
MySQL Database Server
Maven
Setup
Clone the Repository

bash
Copy code
git clone https://github.com/yourusername/userapi.git
cd userapi
Configure the Database

Ensure you have a MySQL database running and create a database named userapi. Update the src/main/resources/application.properties file with your MySQL database configuration.

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/userapi
spring.datasource.username=root
spring.datasource.password=Admin
Build and Run the Application

Use Maven to build and run the application:

bash
Copy code
mvn clean install
mvn spring-boot:run
Application Properties
The application.properties file is located at src/main/resources/application.properties and contains configuration settings for the application.

properties
Copy code
spring.application.name=User Api
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/userapi
spring.datasource.username=root
spring.datasource.password=Admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
file.storage.path=src/main/resources/userdata.xlsx
spring.jpa.properties.hibernate.format_sql=true

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Logging
logging.level.org.springframework=DEBUG
Project Structure
src/main/java/com/example/userapi

model/User.java: Defines the User entity with validation constraints.
repository/UserRepository.java: JPA repository interface for user data operations.
service/UserService.java: Business logic for managing users and interacting with the database and Excel file.
controller/UserController.java: RESTful API endpoints for user operations.
util/ExcelFileHandler.java: Utility class for handling Excel file operations.
UserApiApplication.java: Main class to run the Spring Boot application.
src/main/resources

application.properties: Configuration file for the application.
pom.xml: Maven POM file specifying project dependencies and build configurations.

API Endpoints
POST /api/users/signup

Registers a new user.
Request Body: JSON object with user details.
GET /api/users/admin

Retrieves all users with the role admin.
GET /api/users/user

Retrieves all users with the role user.
GET /api/users/data

Retrieves all users.
PUT /api/users/{id}

Updates the user with the specified ID.
Request Body: JSON object with updated user details.
DELETE /api/users/{id}

Deletes the user with the specified ID.
Build Configuration
The pom.xml file contains the Maven configuration for the project.

xml
Copy code
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.2</version>
    </parent>
    <groupId>com.example</groupId>
    <artifactId>userapi</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>User Api</name>
    <description>User Management API with Spring Boot and MySQL</description>
    <properties>
        <java.version>22</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>annotationProcessor</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.5</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
Notes
Validation: The User entity uses Jakarta Bean Validation annotations for input validation.
Excel File Handling: The ExcelFileHandler class manages reading from and writing to an Excel file, ensuring data consistency between the database and the file.
Logging: The application is configured to use DEBUG level logging for Spring framework classes to help with debugging.
