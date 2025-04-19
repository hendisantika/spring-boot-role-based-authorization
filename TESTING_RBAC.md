# Testing API with Role-Based Access Control (RBAC)

This document provides guidance on how to test the API endpoints with Role-Based Access Control (RBAC) in this Spring
Boot application.

## Overview of RBAC in the Application

The application implements RBAC using Spring Security. There are two roles defined:

- `ADMIN`: Has access to all endpoints
- `USER`: Has limited access (cannot access endpoints requiring ADMIN role)

The application has the following endpoints with different access requirements:

- `/test`: Requires ADMIN role
- `/roleNeeded`: Requires ADMIN role
- `/noRoleNeeded`: Requires only authentication (any role)

## Test Users

The application initializes with two test users:

- Username: `admin`, Password: `admin123`, Role: `ADMIN`
- Username: `user`, Password: `user123`, Role: `USER`

## Testing Methods

### 1. Using rbac.http File

The application includes an `rbac.http` file in the `src/main/resources` directory that contains predefined HTTP
requests for testing RBAC functionality. This is the most convenient way to test if you're using IntelliJ IDEA or VS
Code with the appropriate plugins.

To use the rbac.http file:

#### In IntelliJ IDEA:

1. Open the file `src/main/resources/rbac.http`
2. Click the green "Run" button next to each request
3. View the response in the "Response" tab

#### In VS Code:

1. Install the "REST Client" extension
2. Open the file `src/main/resources/rbac.http`
3. Click "Send Request" above each request
4. View the response in the response panel

The rbac.http file includes tests for:

- Authentication with both Basic Auth and Form Login
- Authorization with different roles
- Unauthorized access scenarios
- Invalid credentials

### 2. Using Automated Tests

The application includes JUnit tests that verify RBAC functionality. These tests use Spring Security's testing support
to simulate authenticated users with different roles.

To run the tests:

```bash
./mvnw test -Dtest=TestControllerTest
```

The tests verify:

- Users with ADMIN role can access all endpoints
- Users with USER role can access only endpoints that don't require ADMIN role
- Unauthenticated users cannot access any protected endpoints

### 2. Manual Testing with cURL

You can test the API endpoints manually using cURL:

#### Testing with ADMIN role:

```bash
# Login with admin user
curl -v -X GET http://localhost:8080/test -u admin:admin123

# Access endpoint requiring ADMIN role
curl -v -X GET http://localhost:8080/roleNeeded -u admin:admin123

# Access endpoint requiring only authentication
curl -v -X GET http://localhost:8080/noRoleNeeded -u admin:admin123
```

#### Testing with USER role:

```bash
# Try to access endpoint requiring ADMIN role (should be forbidden)
curl -v -X GET http://localhost:8080/test -u user:user123

# Try to access another endpoint requiring ADMIN role (should be forbidden)
curl -v -X GET http://localhost:8080/roleNeeded -u user:user123

# Access endpoint requiring only authentication
curl -v -X GET http://localhost:8080/noRoleNeeded -u user:user123
```

#### Testing without authentication:

```bash
# Try to access any protected endpoint (should be unauthorized)
curl -v -X GET http://localhost:8080/test
curl -v -X GET http://localhost:8080/roleNeeded
curl -v -X GET http://localhost:8080/noRoleNeeded
```

### 3. Testing with Postman or Similar Tools

1. Create a new request in Postman
2. Enter the URL (e.g., `http://localhost:8080/test`)
3. Go to the "Authorization" tab
4. Select "Basic Auth" as the type
5. Enter the username and password (e.g., `admin`/`admin123` or `user`/`user123`)
6. Send the request and observe the response

## Understanding the Test Results

- **200 OK**: The request was successful, indicating the user has the required role
- **403 Forbidden**: The user is authenticated but lacks the required role
- **401 Unauthorized**: The user is not authenticated

## Extending the Tests

To test additional endpoints or roles:

1. Add new roles in `DataInitializer.java`
2. Add new users with different role combinations
3. Add new endpoints with different role requirements in `TestController.java`
4. Add corresponding tests in `TestControllerTest.java`

## Troubleshooting

If you encounter issues with the tests:

1. Ensure the application is running (for manual tests)
2. Verify the user credentials are correct
3. Check the security configuration in `SecurityConfig.java`
4. Review the role assignments in `DataInitializer.java`
5. Check the console logs for any security-related messages
