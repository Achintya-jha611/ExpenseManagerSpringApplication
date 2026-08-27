# 💰 Expense Manager

A Spring Boot backend application for managing users and expenses, built with a focus on practical backend engineering concepts and real-world development practices.

The project goes beyond basic CRUD operations and includes **JWT-based authentication, database migrations with Flyway, Docker containerization, automated testing with Testcontainers, and Continuous Integration using Jenkins**.

---

## 🚀 Key Highlights

- 🔐 **Authentication & Security** — JWT-based authentication and protected API endpoints using Spring Security.
- 💰 **Expense Management** — Create, retrieve, update, delete, filter, and manage expenses.
- 👤 **User Management** — User registration and authentication with secure password handling.
- 🗄️ **Database Versioning** — Flyway manages database schema migrations.
- 📖 **API Documentation** — Swagger/OpenAPI provides interactive API documentation.
- 🐳 **Containerized Setup** — Docker and Docker Compose for running the application with MySQL.
- 🧪 **Automated Testing** — Unit, controller, and integration testing using JUnit, Mockito, MockMvc, and Testcontainers.
- 🗃️ **Real Database Integration Tests** — Integration tests run against a real MySQL container instead of an in-memory database.
- 🔄 **Continuous Integration** — Jenkins automatically builds the project and runs the test suite.


---

## 🏗️ Architecture

The application follows a layered architecture to keep responsibilities separated and the codebase easier to maintain and test.

```text
Client
  ↓
Spring Security / JWT Filter
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
MySQL Database
```



### Request Flow

```text
HTTP Request
  ↓
Security Filter
  ↓
Authentication / Authorization
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Database
```

The responsibilities are separated as follows:

- **Controller** — Handles HTTP requests and responses.
- **Service** — Contains the business logic.
- **Repository** — Handles database access using Spring Data JPA.
- **DTOs** — Separates API request and response models from database entities.
- **Global Exception Handling** — Provides consistent error responses.
- **Spring Security** — Secures protected endpoints and handles authentication.

---

## 🛠️ Tech Stack

### Backend
- **Java 21**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **Spring Security**
- **JWT Authentication**

### Database
- **MySQL**
- **Flyway** — Database schema migrations

### Testing
- **JUnit 5**
- **Mockito**
- **MockMvc**
- **Testcontainers** — Integration testing with a real MySQL container

### DevOps & Tools
- **Docker**
- **Docker Compose**
- **Jenkins** — Continuous Integration
- **Maven**
- **Git & GitHub**
- **Swagger / OpenAPI**
- **DBeaver**

---

## 📂 Project Structure

```text
ExpenseManagerSpringApplication/
│
├── src/
│   ├── main/
│   │   ├── java/com/achintya/expensemanager/
│   │   │   ├── config/              # Security, JWT and OpenAPI configuration
│   │   │   ├── controller/          # REST API controllers
│   │   │   ├── dto/                 # Request and response models
│   │   │   ├── mapper/              # Entity ↔ DTO mapping
│   │   │   ├── model/               # JPA entities
│   │   │   ├── repository/          # Database access layer
│   │   │   ├── service/             # Business logic
│   │   │   ├── storage/             # File storage abstraction
│   │   │   ├── ExceptionHandler/    # Custom exceptions and global handling
│   │   │   └── ExpensemanagerApplication.java
│   │   │
│   │   └── resources/
│   │       ├── db/migration/        # Flyway database migrations
│   │       └── application.properties
│   │
│   └── test/
│       └── java/.../expensemanager/
│           ├── ExpenseControllerTest.java
│           ├── ExpenseServiceTest.java
│           └── ExpenseIntegrationTest.java
│
├── Dockerfile                       # Spring Boot container image
├── docker-compose.yml               # Application + MySQL setup
├── Jenkinsfile                      # CI pipeline configuration
├── pom.xml                          # Maven dependencies and build config
├── .env.example                     # Example environment variables
└── mvnw / mvnw.cmd                  # Maven Wrapper


---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java 21**
- **Docker & Docker Compose**
- **Git**

---

### 1. Clone the Repository

```bash
git clone https://github.com/Achintya-jha611/ExpenseManagerSpringApplication.git
cd ExpenseManagerSpringApplication
```

### 2. Configure Environment Variables

Create a `.env` file using the provided example:

```bash
cp .env.example .env
```

Update the environment variables with your local configuration.

---

## 🐳 Running with Docker Compose

The recommended way to run the application is using Docker Compose.

```bash
docker compose up --build
```

This starts:

- The **Spring Boot application**
- A **MySQL database**

Once the application is running, the API will be available at:

```text
http://localhost:8080
```

To stop the containers:

```bash
docker compose down
```

---

## 💻 Running Locally

### 1. Start MySQL

Make sure a MySQL instance is running and your database configuration matches the environment variables used by the application.

### 2. Run the Application

**macOS / Linux:**

```bash
./mvnw spring-boot:run
```

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

---

## 🔐 Security & Authentication

The application uses **Spring Security and JWT (JSON Web Tokens)** to secure protected API endpoints.

### Authentication Flow

```text
User
  ↓
POST /login
  ↓
Authentication Manager
  ↓
JWT Generated
  ↓
JWT Returned to Client
  ↓
Client sends:
Authorization: Bearer <token>
  ↓
JWT Authentication Filter
  ↓
Token Validation
  ↓
Security Context Updated
  ↓
Protected API Access
```

### Security Highlights

- **JWT-based authentication** for stateless API security.
- **BCrypt password encoding** for secure password storage.
- **Stateless session management** using Spring Security.
- A custom **JWT Authentication Filter** validates incoming tokens before protected requests are processed.
- Authenticated users can access protected expense endpoints.
- Public endpoints include user registration, login, and Swagger/OpenAPI documentation.


---

## 🗄️ Database & Migrations

The application uses **MySQL** for persistent data storage and **Flyway** for database schema versioning and migrations.

Flyway automatically manages database schema changes through versioned SQL migration files.

### Migration Files

```text
src/main/resources/db/migration/
│
├── V1__create_initial_schema.sql
└── V2__add_payment_method.sql
```

This approach ensures that database schema changes are:

- **Version controlled**
- **Repeatable across environments**
- **Automatically applied during application startup**
- Easier to maintain as the application evolves

---

## 🧪 Testing

The project includes multiple levels of automated testing to validate different layers of the application.

### Testing Strategy

```text
Unit Tests
   ↓
Service Layer
   ↓
JUnit + Mockito


Controller Tests
   ↓
REST API Layer
   ↓
MockMvc


Integration Tests
   ↓
Spring Boot Application Context
   ↓
Repository + Database
   ↓
Testcontainers + MySQL
```

### Unit Testing

Service layer logic is tested using **JUnit 5** and **Mockito**.

Dependencies such as repositories and other collaborators are mocked to test business logic in isolation.

### Controller Testing

Controller endpoints are tested using **MockMvc** to verify:

- HTTP status codes
- Request and response handling
- JSON responses
- Validation and error scenarios

### Integration Testing

Integration tests use **Spring Boot Test** together with **Testcontainers**.

A real **MySQL container** is started during the test execution, allowing the application to interact with an actual database instead of an in-memory substitute.

```text
Test
  ↓
Spring Boot Context
  ↓
Repository
  ↓
MySQL Testcontainer
```

This provides higher confidence that the application works correctly with the real database environment.

### Running Tests

Run the complete test suite using:

**macOS / Linux:**

```bash
./mvnw test

```
**Windows:**
```bash
mvnw.cmd test
```

---

## 🐳 Docker & Containerization

The application is containerized using **Docker** and can be run together with its MySQL database using **Docker Compose**.

### Docker Setup

```text
Docker Compose
      │
      ├── Spring Boot Application
      │
      └── MySQL Database
```

Docker Compose allows the application and database to run in isolated containers with the required configuration managed through environment variables.

---

## 🔄 Continuous Integration

The project includes a **Jenkins CI pipeline** to automatically build and test the application.

### CI Pipeline Flow

```text
Code Push
   ↓
Jenkins Pipeline Triggered
   ↓
Checkout Source Code
   ↓
Build Application
   ↓
Run Automated Tests
   ↓
Build Success / Failure
```

The CI pipeline helps ensure that new code changes do not break the existing application.

### Jenkins Pipeline

The pipeline is defined using a `Jenkinsfile` and automates the build and test process.

```bash
./mvnw clean test
```

This includes the automated test suite, including integration tests that use **Testcontainers**.

---

## 📖 API Documentation

The API is documented using **Swagger / OpenAPI**, providing an interactive interface to explore and test the available endpoints.

Once the application is running, Swagger UI can be accessed at:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger UI allows you to:

- Explore available API endpoints
- View request and response models
- Check endpoint parameters
- Test API requests directly from the browser
- Authenticate using a JWT token for protected endpoints

---

## 🔮 Future Improvements

Some potential improvements for the project include:

- Add **Redis caching** to improve performance for frequently accessed data.
- Implement **role-based authorization** with different user permissions.
- Add **refresh token support** for improved JWT session management.
- Introduce **API rate limiting** for additional protection.
- Add **monitoring and observability** using tools such as Spring Boot Actuator.
- Deploy the application to a cloud platform.
- Add **API versioning** as the application evolves.
