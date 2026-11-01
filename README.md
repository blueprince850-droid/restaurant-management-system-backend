# Restaurant Management System Backend

**Student:** NTAWUYIRUSHA Prince  
**Student ID:** 25120  
**Project:** Restaurant Management System

Spring Boot + Spring Data JPA + PostgreSQL backend for a restaurant management API. Implements customers, waiters, skills, dining tables, food orders and payments.

## Requirements

- Java 17
- Maven (wrapper included)
- PostgreSQL running on localhost:5432
- A database named `restaurant_db`

## Setup

1. Create the database:

   create database restaurant_db;

2. Configure src/main/resources/application.properties with your database credentials:

   spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

3. Build and run:

   .\mvnw spring-boot:run

   The API runs on http://localhost:8080

## Seed data

On first run, use the CRUD endpoints to create:
- 4 skills: Barista, Bartender, Grill, Sommelier
- 4 dining tables numbered 1-4
- 3 waiters (one with 2 skills, one with 1)
- 5 customers (two with lastName Mukamana)
- 10 food orders across October and November 2026

## Endpoints

Full endpoint list and Postman collection available in `restaurant-management-system-midterm.postman_collection.json`.
