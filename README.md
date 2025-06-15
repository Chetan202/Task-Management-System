# Task-Management-System

![Screenshot 2025-06-08 at 12-43-55 Task Management System](https://github.com/user-attachments/assets/ddab1cc5-3a67-430c-aef2-646bdc211a5d)


![Screenshot 2025-06-08 at 12-44-11 Dashboard - Task Management System](https://github.com/user-attachments/assets/ddd4ab62-a024-47b7-837b-441b51b118da)


![Screenshot 2025-06-08 at 12-44-24 Edit Task - Task Management System](https://github.com/user-attachments/assets/a13ce0ab-f7d4-4f3a-93e0-b65a45c462c9)


![Screenshot 2025-06-08 at 12-44-38 New Task - Task Management System](https://github.com/user-attachments/assets/671b6bcd-bcb7-4016-8a33-15d13317e9b8)


# Task Management System

A simple web‑based Task Management System built with Spring Boot and MySQL.  
Track, add, edit, and delete tasks through a clean, responsive UI.

---

## 📋 Table of Contents

1. [Features](#-features)  
2. [Screenshots](#-screenshots)  
3. [Tech Stack](#-tech-stack)  
4. [Prerequisites](#-prerequisites)  
5. [Getting Started](#-getting-started)  
   - [Clone the repo](#clone-the-repo)  
   - [Configure the database](#configure-the-database)  
   - [Build & Run](#build--run)  
6. [API Endpoints](#-api-endpoints)  
7. [Project Structure](#-project-structure)  
8. [Contributing](#-contributing)  
9. [License](#-license)  
10. [Contact](#-contact)

---

## 🛠 Features

- **List Tasks**: View all your tasks at a glance  
- **Create Task**: Add new tasks with title, description, due date  
- **Edit Task**: Update task details or mark as complete  
- **Delete Task**: Remove tasks you no longer need  
- **Responsive UI**: Works on both desktop and mobile screens  
- **MySQL Persistence**: Data stored in MySQL database  


---

## 🔧 Tech Stack

- **Backend**: Spring Boot (Java 17+)  
- **Database**: MySQL  
- **Build Tool**: Maven  
- **Frontend**: Thymeleaf, Bootstrap (or your choice CSS framework)  
- **Version Control**: Git & GitHub  

---

## 📦 Prerequisites

- Java 17 (or newer)  
- Maven 3.6+  
- MySQL 8.0+  
- Git  

---

## 🚀 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/Chetan202/Task-Management-System.git
cd Task-Management-System

```
### 🔗 API Endpoints
| Method | Endpoint      | Description             |
| ------ | ------------- | ----------------------- |
| GET    | `/tasks`      | List all tasks          |
| GET    | `/tasks/{id}` | Get task by ID          |
| POST   | `/tasks`      | Create a new task       |
| PUT    | `/tasks/{id}` | Update an existing task |
| DELETE | `/tasks/{id}` | Delete a task           |
```
```
### 📁 Project Structure
```
├── mvnw*                       # Maven wrapper
├── pom.xml                     # Project dependencies
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.taskmanagement
│   │   │       ├── controller   # Spring MVC controllers
│   │   │       ├── model        # JPA entity classes
│   │   │       ├── repository   # Spring Data JPA repos
│   │   │       ├── service      # Business logic
│   │   │       └── TaskManagementSystemApplication.java
│   │   └── resources
│   │       ├── templates       # Thymeleaf HTML templates
│   │       └── application.properties
└── README.md

```
### 🤝 Contributing
```
Contributions are welcome! Please:

    Fork the project

    Create your feature branch (git checkout -b feature/your-feature)

    Commit your changes (git commit -m 'Add some feature')

    Push to the branch (git push origin feature/your-feature)

    Open a Pull Request

```
### 📄 License
```
This project is licensed under the MIT License.
```
### 📬 Contact
```
Created by Chetan Jha – feel free to reach out!

    GitHub: @Chetan202

    Email: chetanjha888@gmail.com


