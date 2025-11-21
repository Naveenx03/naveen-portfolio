# 🎓 Student Management System (Java + MySQL)

A simple console-based Student Management System built using Core Java, JDBC, and MySQL.  
This project manages Students through a clean DAO + Service + Utility + Main structure.

## 🚀 Features

### 🧑‍🎓 Student Management
- Add new students  
- View all students  
- Update student details  
- Delete students  
- Auto-increment student IDs  

### 📂 Additional Features
- Modular DAO implementation  
- Centralized utility functions  
- Clean service layer handling business logic  

## 📁 Project Structure

src/
└── com/
└── student/
├── dao/
│ ├── StudentDAO.java
│ └── StudentDAOImpl.java
│
├── model/
│ └── Student.java
│
├── service/
│ └── StudentService.java
│
├── util/
│ └── Utils.java
│
└── main/
└── App.java


## 🗄️ Database Schema (MySQL)

### Table: students
- id (INT, PK, Auto Increment)  
- name (VARCHAR)  
- email (VARCHAR)  
- course (VARCHAR)  
- year (INT)

## 🛠️ Technologies Used
- Java (JDK 8+)  
- MySQL 8  
- JDBC  
- VS Code  
