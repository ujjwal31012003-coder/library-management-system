# Library Management System

A full-stack Library Management System developed using React, Spring Boot, and PostgreSQL. The application helps manage students, books, librarians, and book issuing and returning through a web-based interface.

## Features

- **Authentication:** Login system with JWT-based authentication.
- **Dashboard:** A central interface for accessing library management modules.
- **Student Management:** Manage student records.
- **Book Management:** Manage book information and availability.
- **Librarian Management:** Manage librarian records.
- **Book Issue and Return:** Handle book issuing and returning operations.
- **REST APIs:** Backend APIs built with Spring Boot.
- **Database Migrations:** Database schema managed using Flyway.
- **Security:** Spring Security and BCrypt password hashing.

## Technology Stack

### Frontend
- React
- JavaScript
- Axios
- HTML
- CSS
- Vite

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Maven

### Database
- PostgreSQL
- Flyway

## Project Structure

```text
library-management-system/
├── library-frontend/
│   ├── public/
│   ├── src/
│   ├── package.json
│   └── vite.config.js
│
├── library-management/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── db/migration/
│   │   └── test/
│   └── pom.xml
│
└── README.md
```

## Prerequisites

Install the following software before running the project:

- Java JDK compatible with the project's Spring Boot version
- Node.js and npm
- PostgreSQL
- IntelliJ IDEA or another Java IDE
- Git

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/ujjwal31012003-coder/library-management-system.git
```

Navigate into the project folder:

```bash
cd library-management-system
```

### 2. Configure PostgreSQL

Start PostgreSQL and create a database named:

```sql
CREATE DATABASE library_db;
```

The application uses environment variables for database credentials.

Configure these variables:

```text
DB_USERNAME=postgres
DB_PASSWORD=your_postgresql_password
```

Replace the example password with your own local PostgreSQL password.

**Do not commit your actual database password to GitHub.**

### 3. Configure the JWT Secret

Set the environment variable:

```text
JWT_SECRET_KEY=your_jwt_secret
```

Use a secure secret compatible with the application's JWT configuration. Keep it private and do not commit it to GitHub.

Configure these environment variables in your IDE before running the backend.

### 4. Run the Backend

Open the inner `library-management` folder as a Maven project in IntelliJ IDEA.

Make sure PostgreSQL is running and the required environment variables are configured.

Run the main Spring Boot application class:

```text
LibraryManagementApplication.java
```

The backend runs at:

```text
http://localhost:8080
```

Flyway manages the database migrations configured in the backend project.

### 5. Run the Frontend

Open a terminal in the `library-frontend` directory:

```bash
cd library-frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Open the frontend in your browser:

```text
http://localhost:5173
```

## Application Modules

| Module | Description |
|---|---|
| Authentication | Login and JWT-based authentication |
| Dashboard | Main application interface |
| Students | Student record management |
| Books | Book record management |
| Librarians | Librarian record management |
| Book Issues | Book issuing and returning |

## Future Improvements

- Deploy the frontend and backend for public access.
- Add automated tests for important business operations.
- Improve application monitoring and error reporting.
- Add further reporting and library analytics features.

## Author

**Ujjwal Singh**

GitHub: [ujjwal31012003-coder](https://github.com/ujjwal31012003-coder)

## License

This project was developed for educational and portfolio purposes.