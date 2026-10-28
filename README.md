# Spring Boot Role-Based Authorization

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-role-based-authorization/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-role-based-authorization/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

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

## Prerequisites

- JDK 25 (e.g. [Eclipse Temurin](https://adoptium.net/))
- No Maven install needed — use the bundled Maven Wrapper (`./mvnw`)

## Running the Application

```bash
./mvnw spring-boot:run
```

## Running the Tests

```bash
./mvnw test
```

## Technologies Used

- Java 25
- Spring Boot 4.1
- Spring Security
- Spring Data JPA
- H2 Database
- JUnit 5 (with `spring-boot-starter-webmvc-test` and `spring-boot-starter-security-test`)
- Lombok

## Continuous Integration

GitHub Actions ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) runs on every push and pull request
to `main`:

- Builds and tests with Temurin JDK 25 via `./mvnw -B verify`
- Caches Maven dependencies and cancels superseded runs
- Uploads Surefire reports when tests fail
- Submits the Maven dependency graph on pushes to `main` for better Dependabot alerts

## Dependency Updates

Dependabot ([`.github/dependabot.yml`](.github/dependabot.yml)) checks Maven and GitHub Actions dependencies daily at
05:00 (Asia/Jakarta):

- Spring Boot and Spring Framework updates are grouped into their own PRs
- Remaining Maven minor/patch updates are batched into one PR
- GitHub Actions updates are batched into one PR
- PRs are labelled and use conventional commit prefixes (`build(deps)`, `ci(deps)`)
