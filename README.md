# E-commerce Backend

A production-style E-commerce Backend Application built using Java, Spring Boot, Spring Security, JPA/Hibernate, and MySQL. It provides RESTful APIs for managing users, products, categories, shopping carts, orders, payments, and inventory while following clean architecture and backend best practices.

## Features

- Role-Based Authorization
- Product Management
- Category Management
- Shopping Cart Management
- Order Management
- Payment Module
- Address Management
- RESTful API Design
- Pagination & Sorting
- Input Validation
- Global Exception Handling
- Soft Delete
- Auditing (Created & Updated Timestamps)
- Unit Testing

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- ModelMapper
- JUnit 5

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
├── exception
└── validation
```

## Architecture

Controller → Service → Repository → MySQL Database

## Future Enhancements

- JWT Authentication
- Docker
- Swagger / OpenAPI
- Redis Caching