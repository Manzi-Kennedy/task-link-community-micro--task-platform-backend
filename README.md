# Task Link Community Micro Task Platform

## Student

- **Name:** MANZI Kennedy
- **Student ID:** 27600

## Project

Task Link Community Micro Task Platform is a Spring Boot REST API for managing posters, helpers, zones, skills, tasks, ratings, and dashboard reports.

## Requirements

- Java 17
- Maven Wrapper
- PostgreSQL

## Run the API

1. Create a PostgreSQL database named `tasklink`.
2. Open `src/main/resources/application.properties` and configure:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/tasklink
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD
   ```

3. From the project directory, start the API:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

4. The API runs on `http://localhost:8085`.
