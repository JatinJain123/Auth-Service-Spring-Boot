# 🔐 Spring Boot Auth Service

A stateless and secure authentication backend built using **Spring Boot**, **MongoDB**, and **JWT**.  
It supports user registration, login, and profile access using JWT-based authentication. Passwords are hashed securely using BCrypt. Swagger UI is integrated for easy testing and documentation.

---

## 🚀 Features

- ✅ User Registration and Login
- ✅ JWT-based authentication
- ✅ Password hashing with BCrypt
- ✅ Protected routes with Spring Security
- ✅ MongoDB integration
- ✅ Swagger UI for API testing
- ✅ Clean layered architecture with DTOs & Exception Handling

---

## 🧠 Tech Stack

- Java 17
- Spring Boot 3.5.0
- Spring Security
- MongoDB (Spring Data MongoDB)
- JWT (JJWT 0.11.5)
- BCryptPasswordEncoder
- Springdoc OpenAPI (Swagger UI) — `2.8.9`

---

## 📄 API Endpoints

### 🟢 Public

| Method | Endpoint              | Description                |
|--------|-----------------------|----------------------------|
| POST   | `/api/auth/register`  | Register a new user        |
| POST   | `/api/auth/login`     | Login and get JWT token    |

### 🔐 Protected

| Method | Endpoint                | Description                  |
|--------|-------------------------|------------------------------|
| GET    | `/api/users/my-profile` | Get authenticated user's info |

> 📌 Use `Authorization: Bearer <your-token>` header for protected endpoints.

---

## 🧪 Sample Request Flow

### Register
```
POST /api/auth/register

{
  "fullName": "Alice Smith",
  "email": "alice@example.com",
  "password": "secure123"
}
```

### Login
```
POST /api/auth/login

{
  "email": "alice@example.com",
  "password": "secure123"
}
```

returns the jwt token

### Access Profile
```
GET /api/users/my-profile
Authorization: Bearer <your-token>
```
---

## Swagger UI

Test the API visually using Swagger:  
http://localhost:8080/swagger-ui/index.html

---

## How to Run the Project

### Prerequisites

- Java 17+
- Maven
- MongoDB (Atlas)

### application.properties Configuration

Before running the app, update the following properties in your `application.properties` file:

```
spring.data.mongodb.uri=your-mongodb-uri
spring.data.mongodb.database=your-mongodb-database
jwt.secret=your-hs256-secret-key
```

---

### Run the App

Navigate to your project directory:

cd your-project-folder

Build and run the app:

```
./mvnw clean install  
./mvnw spring-boot:run
```

App runs at:  
http://localhost:8080  
Swagger UI:  
http://localhost:8080/swagger-ui/index.html