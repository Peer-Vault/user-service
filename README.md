# Peer Vault - User Service

## 🚀 Overview
The **User Service** is a core microservice within the Peer Vault ecosystem, responsible for managing user accounts, authentication, and authorization. It handles user registration, login, JWT token generation and validation, and provides user-specific information.

---

## ✨ Features
* **User Registration:** Allows new users to create accounts.
* **User Authentication:** Securely authenticates users and generates JWT tokens.
* **JWT Token Validation:** Validates incoming JWT tokens for secure access to resources.
* **Current User Retrieval:** Provides details of the currently authenticated user.
* **User Information Retrieval:** Allows fetching user details by ID.
* Integrates with **Spring Security** for robust authentication and authorization.
* Registers with **Service Discovery** for seamless integration with other microservices.

---

## 🛠 Tech Stack
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT (JSON Web Tokens)
* H2 Database (for development/testing) / PostgreSQL (for production)
* Spring Cloud Netflix Eureka Client
* Docker & Docker Compose (optional for containerized deployment)

---

## 🏗 Installation & Setup

### Prerequisites
* Java 17 or higher
* Maven
* Git
* Running **Config Server**
* Running **Service Discovery Server**
* Docker and Docker Compose (optional)

### Backend Setup

1.  **Clone the User Service repository:**
    ```bash
    git clone [https://github.com/Peer-Vault/user-service.git](https://github.com/Peer-Vault/user-service.git)
    cd user-service
    ```

2.  **Configure the User Service `application.yml`** (located in the `src/main/resources` folder) to connect to your Config Server and register with Service Discovery. An example `application.yml` would look like this:
    ```yaml
    spring:
      application:
        name: userservice
      config:
        import: optional:configserver:http://localhost:8071/

    server:
      port: 8081 # Default port for the User Service
    ```
    Additional configurations for database connection, JWT secret, etc., will be fetched from the Config Server.

3.  **Ensure necessary Java classes are in place:**
    This project relies on various components for authentication and user management. Verify the presence of:
    * `com.peer.vault.user_service.controller.AuthController`
    * `com.peer.vault.user_service.controller.UserController`
    * `com.peer.vault.user_service.service.AuthService`
    * `com.peer.vault.user_service.config.CustomUserDetails`
    * `com.peer.vault.user_service.domain.UserCredential`
    * `com.peer.vault.user_service.domain.AuthRequest`
    * `com.peer.vault.user_service.util.JwtService`

4.  **Run the User Service:**

    **Run Locally**
    ```bash
    mvn spring-boot:run
    ```


---

## 🎯 Usage
* Start the **Config Server**, **Service Discovery Server**, and **Gateway Server** before starting the User Service.
* All interactions with the User Service should ideally go through the Gateway Server (e.g., `http://localhost:8080/user/...`).

* **Register a new user:**
    * **Endpoint:** `POST /user/auth/register`
    * **Body:** `{"name": "testuser", "email": "test@example.com", "password": "password123"}`
    * **Response:** Created user details.

* **Login and get JWT token:**
    * **Endpoint:** `POST /user/auth/login`
    * **Body:** `{"email": "test@example.com", "password": "password123"}`
    * **Response:** JWT token string.

* **Validate JWT token:**
    * **Endpoint:** `GET /user/auth/validate?token={your_jwt_token}`
    * **Response:** "Token is valid" or an error.

* **Get current authenticated user details:**
    * **Endpoint:** `GET /user/auth/current-user`
    * **Headers:** `Authorization: Bearer {your_jwt_token}`
    * **Response:** Details of the logged-in user.

* **Get user by ID (requires authentication):**
    * **Endpoint:** `GET /user/userinfo/getUserById?id={user_id}`
    * **Headers:** `Authorization: Bearer {your_jwt_token}`
    * **Response:** User details.

---

## 🤝 Contributing
Contributions and suggestions are welcome! Feel free to open issues or submit pull requests to improve the project.

---

## 👨‍💻 Author
Developed and maintained by the Peer Vault team.

---

🌟 Star this repo if you find it helpful! 🌟
