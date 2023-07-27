# Spring Boot and Microservices Practice Project

This practice project is an example application based on Spring Boot and microservices. The main objective of the project is to learn and familiarize yourself with various components and patterns used in developing microservices applications.

## Project Structure

The project is divided into several microservices, each with its specific functionality. Below is the project structure:

1. `customer-service`: Microservice that handles customer management.
2. `shopping-service`: Microservice responsible for order management.
3. `product-service`: Microservice that manages the product catalog.
4. `config-server`: Centralized configuration server using Spring Cloud Config.
5. `discovery-server`: Service discovery server based on Eureka for managing microservices registration and discovery.
6. `gateway-api`: API Gateway that acts as a single entry point for all microservices.

## Features and Technologies Used

- Spring Boot for creating microservices.
- Spring Cloud Config for centralized configuration of microservices.
- Eureka as a service discovery server to facilitate scalability and high availability.
- Spring Cloud Gateway for request routing and filtering.
- Feign for communication between microservices.
- Resilience4j for implementing fault tolerance and resilience in calls to remote microservices.
- Actuator provides a series of pre-configured endpoints for monitoring and managing your microservices.