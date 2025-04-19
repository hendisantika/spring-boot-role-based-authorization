# Spring Boot Role-Based Authorization

This project demonstrates how to implement Role-Based Access Control (RBAC) in a Spring Boot application using Spring
Security.

## Features

- Role-based access control for API endpoints
- Method-level security using `@PreAuthorize` annotations
- Basic authentication with username/password
- JUnit tests for verifying RBAC functionality

## Project Structure

- **Controller**: Contains REST endpoints with different authorization requirements
- **Security Config**: Configures Spring Security for the application
- **Domain Models**: User and Role entities
- **Data Initialization**: Creates default roles and users for testing

## Default Users

The application initializes with two test users:

- Username: `admin`, Password: `admin123`, Role: `ADMIN`
- Username: `user`, Password: `user123`, Role: `USER`

## API Endpoints

- `/test`: Returns the authenticated user (requires ADMIN role)
- `/roleNeeded`: Returns a message (requires ADMIN role)
- `/noRoleNeeded`: Returns a message (requires only authentication)

## Testing RBAC

For detailed instructions on how to test the RBAC functionality, see [TESTING_RBAC.md](TESTING_RBAC.md).

## Running the Application

```bash
./mvnw spring-boot:run
```

## Running the Tests

```bash
./mvnw test
```

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- JUnit 5
- Lombok
