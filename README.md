# 📦 Gadget Inventory Management System

A comprehensive backend API built with **Kotlin**, **Micronaut**, and **PostgreSQL** for learning modern JVM backend development. This project implements user authentication and gadget inventory management with CRUD operations.

## ✅ Implemented Features

### User Management
- ✅ User registration with email validation
- ✅ User CRUD operations (Create, Read, Update, Delete)
- ✅ User search by name or email
- ✅ Role-based user system (USER, ADMIN)
- ✅ Strong password validation (16+ chars, digits, special chars)

### Authentication System
- ✅ Secure password hashing with Argon2
- ✅ User registration endpoint
- ✅ Authentication infrastructure setup
- 🔄 Login endpoint (in progress)
- ⏳ JWT token generation
- ⏳ Password reset functionality

### Infrastructure
- ✅ Clean architecture (Controllers → Services → Repositories → Entities)
- ✅ PostgreSQL database with JPA/Hibernate
- ✅ Input validation with Jakarta Bean Validation
- ✅ Custom exception handling
- ✅ Flyway database migrations setup
- ✅ Docker containerization support

## 🎯 Planned Features

### Core Business Logic
- ⏳ Gadget entity model (name, brand, model, serial, price, status)
- ⏳ Gadget CRUD operations
- ⏳ Gadget search and filtering
- ⏳ User-gadget ownership relationships
- ⏳ Inventory status tracking (AVAILABLE, IN_USE, MAINTENANCE, RETIRED)

### Advanced Features
- ⏳ Sales tracking and profit calculation
- ⏳ Low-stock alerts
- ⏳ Sales history and reporting
- ⏳ File upload for gadget images
- ⏳ API documentation with OpenAPI/Swagger

## 🔧 Technology Stack

- 🧠 **Kotlin 1.9.25** — Modern JVM language with null safety
- ⚡ **Micronaut 4.5.3** — Lightweight, fast JVM framework
- 🐘 **PostgreSQL** — Robust relational database
- 🔐 **Micronaut Security + JWT** — Authentication and authorization
- 🛡️ **Argon2** — Secure password hashing
- 📊 **Hibernate JPA** — Object-relational mapping
- 🚀 **Flyway** — Database migration management
- 🐳 **Docker** — Containerization
- ✅ **JUnit 5** — Testing framework

## 🚀 Getting Started

### Prerequisites
- Java 21+
- PostgreSQL database
- Gradle (wrapper included)

### Database Setup
```sql
CREATE DATABASE gadgetdb;
CREATE USER postgres WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE gadgetdb TO postgres;
```

### Running the Application
```bash
# Clone the repository
git clone <repository-url>
cd gadget-inventory

# Run the application
./gradlew run

# Run tests
./gradlew test

# Build Docker image
./gradlew dockerBuild
```

### Configuration
Update `src/main/resources/application.yml`:
```yaml
datasources:
  default:
    url: jdbc:postgresql://localhost:5432/gadgetdb
    username: postgres
    password: admin
```

## 📚 API Documentation

### Current Endpoints

#### Authentication
```
POST /auth/signup     - Register new user
POST /auth/login      - User login (in development)
PATCH /auth/reset-password    - Reset password (planned)
PATCH /auth/forget-password   - Forgot password (planned)
```

#### Users
```
GET    /users/all?query=    - Get all users or search
GET    /users/{id}          - Get user by ID
PATCH  /users/{id}          - Update user
DELETE /users/{id}          - Delete user
```

#### Planned Gadget Endpoints
```
GET    /gadgets           - List gadgets with filtering
POST   /gadgets           - Create new gadget
GET    /gadgets/{id}      - Get gadget details
PATCH  /gadgets/{id}      - Update gadget
DELETE /gadgets/{id}      - Delete gadget
```

## 🌱 Learning Objectives

This project serves as a comprehensive learning platform for:

### Backend Development
- RESTful API design and implementation
- Database design and relationships
- Authentication and authorization
- Input validation and error handling
- Testing strategies (unit, integration)

### Kotlin & JVM Ecosystem
- Kotlin language features and idioms
- Dependency injection patterns
- JPA/Hibernate ORM
- Micronaut framework capabilities

### DevOps & Production
- Docker containerization
- Database migrations
- Configuration management
- Monitoring and logging

## 📁 Project Structure

```
src/
├── main/kotlin/com/example/
│   ├── controller/          # REST controllers
│   ├── service/            # Business logic
│   ├── repository/         # Data access layer
│   ├── model/              # JPA entities
│   ├── dto/                # Data transfer objects
│   ├── exception/          # Error handling
│   └── util/               # Utilities
└── test/                   # Test cases
```

## 🛠 Development Status

**Current Phase**: Authentication & User Management  
**Next Phase**: Core Business Logic (Gadgets)  
**Progress**: ~40% complete

See [TODO.md](TODO.md) for detailed task tracking and progress updates.

## 🤝 Contributing

This is a learning project, but suggestions and improvements are welcome! Feel free to:
- Report issues
- Suggest improvements
- Share learning resources
- Provide code review feedback

## 📄 License

This project is for educational purposes. Feel free to use it as a learning reference.
