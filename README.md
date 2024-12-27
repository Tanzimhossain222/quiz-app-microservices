# Quiz App (Microservices Architecture)

## Overview
The **Quiz App** has been transformed from a monolithic architecture to a **microservices-based architecture** for better scalability, modularity, and maintainability. The application is designed to offer a seamless quiz service, allowing users to create quizzes, answer questions, and receive real-time results. With the new architecture, the system is more robust and ready to handle modern distributed workloads.

## Key Features
- **Quiz Creation Service**: Users can create custom quizzes with categories, titles, and questions.
- **Question Management Service**: Handles CRUD operations for quiz questions.
- **Result Calculation Service**: Processes quiz answers and calculates real-time results.
- **Service Discovery**: All services are registered with Eureka Server for dynamic discovery and load balancing.
- **Inter-Service Communication**: Simplified using OpenFeign clients for HTTP-based communication between services.
- **Fault Tolerance**: With built-in exception handling and service-level isolation.

## Microservices Architecture
The application is now composed of the following services:
1. **Service Registry (Eureka Server)**: Enables service discovery and registration of microservices.
2. **Quiz Service**: Manages quizzes and their metadata.
3. **Question Service**: Handles questions for each quiz, including CRUD operations.
4. **Result Service (Optional)**: Computes results for completed quizzes.
5. **API Gateway (Planned)**: Centralized entry point for routing and security (future work).

## Technologies Used
This application leverages a modern stack for microservices development:
- **Java 23**: Leveraging the latest Java features for better performance and readability.
- **Spring Boot**: Framework to simplify building production-grade services.
- **Spring Cloud Netflix Eureka**: For service discovery and load balancing.
- **OpenFeign**: Simplified REST clients for inter-service communication.
- **Spring Data JPA**: For seamless database interaction.
- **PostgreSQL**: Storing quiz data, questions, and user responses.
- **Maven**: Build tool for dependency management and packaging.

## Getting Started

### Prerequisites
- **Java 23** or later
- **PostgreSQL** (or access to a PostgreSQL database)

### Installation
1. **Clone the repository**:
    ```bash
    git clone https://github.com/Tanzimhossain222/quiz-app.git
    cd quiz-app
    ```

2. **Build the application**:
   Run the following to build the project:
    ```bash
    mvn clean install
    ```

3. **Set up PostgreSQL**:
   - Create separate databases (if needed) for each service (e.g., `quiz_service_db`, `question_service_db`).
   - Update the `application.properties` file in each service with the appropriate database credentials.

4. **Run the Services**:
   Start the services in the following order:
   - **Eureka Server**:
     ```bash
     cd service-registry
     mvn spring-boot:run
     ```
   - **Other Services** (Quiz, Question, etc.):
     Navigate to each service folder and run:
     ```bash
     mvn spring-boot:run
     ```

5. **Access the Application**:
   - **Eureka Dashboard**: `http://localhost:<eureka-server-port>/`
   - API endpoints: Use the endpoints defined in each service.

### Future Enhancements
- **API Gateway**: Centralized gateway for routing and security.
- **Dockerization**: Containerizing services for better portability.
- **Kubernetes Deployment**: Orchestrating services for scalability.
- **Integration Tests**: Ensuring inter-service communication works as expected.
