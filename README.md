# Bank

A simple banking backend service built with Spring Boot. It manages bank accounts and money transfers through a REST API. It is a learning project for practicing production-style backend development: persistence, testing, CI, containerization, event-driven notifications and security.

## Tech Stack

**Current**
- Java 21
- Spring Boot 4.1 (Spring Web MVC)
- Maven (via Maven Wrapper)
- JUnit 5

**Planned**
- PostgreSQL + Spring Data JPA
- Flyway (database migrations)
- Bean Validation
- Testcontainers (integration tests)
- GitHub Actions (CI)
- Docker / Docker Compose
- Apache Kafka (notifications)
- Spring Security + JWT

## Roadmap

| Sprint | Feature | Description |
|--------|---------|-------------|
| 1 | **Accounts** | Domain model for bank accounts: create an account, get balance, deposit and withdraw, in-memory storage. |
| 2 | **Database** | Store data in PostgreSQL with Spring Data JPA and manage the schema with Flyway migrations. |
| 3 | **REST API** | REST endpoints for accounts, DTOs, request validation, global error handling. |
| 4 | **Transfers** | Money transfers between accounts: transactional, balance checks, transfer history. |
| 5 | **Tests & CI** | Unit and integration tests (Testcontainers), GitHub Actions pipeline that builds and tests every push. |
| 6 | **Docker** | Dockerfile for the app and Docker Compose for the app, PostgreSQL and Kafka together. |
| 7 | **Kafka notifications** | Publish transfer events to Kafka and handle them in a notification consumer. |
| 8 | **Security** | Authentication and authorization with Spring Security and JWT; users can only access their own accounts. |

## Getting Started

**Requirements:** JDK 21, with the `JAVA_HOME` environment variable pointing to it.

The Maven project lives in the `bank/` subfolder, so run all commands from there.

**Linux / macOS (bash):**
```bash
cd bank
./mvnw test              # run tests
./mvnw spring-boot:run   # run the application
```

**Windows (PowerShell):**
```powershell
cd bank
.\mvnw.cmd test              # run tests
.\mvnw.cmd spring-boot:run   # run the application
```

The application starts on `http://localhost:8080`.
