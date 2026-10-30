# 🏨 Hostelify – Multi-Hostel Management System

**A Secure, Scalable Backend for Hostel Administration and Accommodation Management**

Hostelify is a comprehensive multi-hostel management and accommodation discovery platform designed to simplify and centralize hostel operations. Built using **Java 21 and Spring Boot**, the backend provides secure REST APIs to manage multiple hostel properties, room and bed allocations, students, staff, payments, and maintenance requests.

The system follows a layered architecture, emphasizing maintainability, security, transactional consistency, and scalable business logic.

---

## 🚀 Key Features

### 🔐 Authentication & Authorization
- JWT-based authentication and secure API access.
- Role-Based Access Control (RBAC).
- Support for multiple user roles:
  - Property Owners
  - Wardens
  - Staff
  - Students

### 🏢 Multi-Hostel Management
- Centralized management of multiple hostel properties.
- Hostel information and facility management.
- Floor and room organization.
- Property-specific administration.

### 🛏️ Room & Bed Allocation
- Room and bed management.
- Capacity and occupancy tracking.
- Bed availability monitoring.
- Allocation and reservation management.
- Prevention of invalid or conflicting allocations.

### 👨‍🎓 Student & Staff Management
- Student registration and profile management.
- Staff directory and account management.
- Secure document handling and identity verification.
- Staff onboarding and credential management.

### 📧 Automated Email Notifications
- Spring Mail integration using SMTP.
- Automated staff onboarding emails.
- Account credential notifications.
- System alerts and email communication.

### 💳 Payment Management
- Payment transaction recording.
- Hostel fee tracking.
- Payment history management.
- Payment status monitoring.

### 📝 Complaint & Maintenance Management
- Student complaint submission.
- Maintenance request tracking.
- Complaint status management.
- Issue resolution workflow.

---

## 🛠️ Technology Stack

| Category | Technologies |
|---|---|
| Programming Language | Java 21 |
| Backend Framework | Spring Boot 3.2+ |
| Security | Spring Security, JWT |
| ORM | Hibernate |
| Data Access | Spring Data JPA |
| Database | MySQL |
| Email Services | Spring Mail, SMTP |
| Build Tool | Apache Maven |
| Architecture | Layered Architecture |
| API Style | REST APIs |

---

## 🏗️ Backend Architecture

The application follows a **Layered Architecture**, separating API handling, business logic, data access, and persistence responsibilities.

### Project Structure

```text
hostelify-backend/
│
├── src/
│   ├── main/
│   │   ├── java/com/hostelify/backend/
│   │   │   │
│   │   │   ├── config/          # Application and security configuration
│   │   │   ├── controller/      # REST API endpoints
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── model/           # JPA entities
│   │   │   ├── repository/      # Database access layer
│   │   │   ├── service/         # Business logic
│   │   │   └── exception/       # Global exception handling
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/                    # Unit and integration tests
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

### Request Processing Flow

```text
        Client Application
                |
                v
        REST Controller
                |
                v
          Service Layer
                |
                v
        Repository Layer
                |
                v
          MySQL Database
```

**Architecture Components**

- **Controller Layer:** Handles incoming HTTP requests and returns API responses.
- **Service Layer:** Implements business rules and application logic.
- **Repository Layer:** Handles database operations using Spring Data JPA.
- **Model Layer:** Defines database entities and relationships.
- **DTO Layer:** Manages data transferred between the client and backend.
- **Exception Layer:** Provides centralized exception handling.

---

## ⚙️ Getting Started

Follow these instructions to set up and run the backend locally.

### Prerequisites

Ensure the following software is installed:

- [Java Development Kit 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [MySQL](https://dev.mysql.com/downloads/)
- [Apache Maven](https://maven.apache.org/download.cgi) (optional if using Maven Wrapper)
- [Git](https://git-scm.com/)

### 1. Clone the Repository

```bash
git clone https://github.com/sawaniThiwandika/hostel-management-system-backend.git

cd hostel-management-system-backend
```

### 2. Create the Database

Open MySQL and execute the following SQL command:

```sql
CREATE DATABASE hostelify_db;
```

### 3. Configure Application Properties

Navigate to:

```text
src/main/resources/application.properties
```

Configure your database, email, JWT, and application settings.

**Example configuration:**

```properties
# Server Configuration
server.port=8080

# Application Configuration
spring.application.name=hostelify-backend

# Java 21 Virtual Threads
spring.threads.virtual.enabled=true

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hostelify_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_database_password

# Hibernate / JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Email Configuration (Gmail SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# JWT Configuration
jwt.secret=your_secure_jwt_secret_key
jwt.expiration=86400000
```

**Important:**
- Replace placeholder values with your actual credentials.
- Use a Gmail App Password for SMTP authentication.
- Never commit database credentials, email passwords, or JWT secrets to GitHub.
- Use environment variables or a secure secrets manager for production configuration.
- Consider Flyway or Liquibase for production database migrations instead of relying on `ddl-auto=update`.

### 4. Build the Application

Using Maven:

```bash
mvn clean install
```

Alternatively, use the Maven Wrapper.

**Windows PowerShell:**

```powershell
.\mvnw.cmd clean install
```

**Linux / macOS:**

```bash
./mvnw clean install
```

### 5. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or using the Maven Wrapper:

**Windows PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

Once started, the backend server will be available at:

```text
http://localhost:8080
```

---

## 🔒 Security Considerations

Hostelify incorporates security mechanisms to protect application resources and user information.

- JWT-based authentication.
- Role-Based Access Control.
- Spring Security integration.
- Secure password handling.
- Protected API endpoints.
- Controlled access to application resources.

Production deployments should additionally implement HTTPS, secure secret management, appropriate CORS policies, and comprehensive input validation.

---

## 🧪 Testing

Run the test suite using:

```bash
mvn test
```

Or with the Maven Wrapper:

```powershell
.\mvnw.cmd test
```

---

## 📄 License

This project is distributed under the **MIT License**. See the [LICENSE](LICENSE) file for more information.

---
