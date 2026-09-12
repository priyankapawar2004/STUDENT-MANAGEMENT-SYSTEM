# Student Management System

A web-based Student Management System developed using Java and Spring Boot. The application allows users to manage student records and course enrollments through a simple web interface.

## Features

- Add student records
- View student records
- Edit and update student information
- Delete student records
- Student data validation
- Pagination
- Course enrollment management
- View enrolled students

## Technologies Used

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Thymeleaf
- HTML & CSS
- Maven
- Git & GitHub

## Project Architecture

The project follows a layered architecture:

Controller → Service → Repository → MySQL Database

## Database

MySQL is used for storing student and course information.

Database name:

`student_db`

Database credentials are configured using an environment variable and are not stored in the repository.

## How to Run

1. Install Java 21 and MySQL.
2. Clone this repository.
3. Create a MySQL database named `student_db`.
4. Configure the `DB_PASSWORD` environment variable with your MySQL password.
5. Run the Spring Boot application.
6. Open `http://localhost:8081` in your browser.

## Developer

**Priyanka Pawar**

GitHub: [priyankapawar2004](https://github.com/priyankapawar2004)
