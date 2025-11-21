# 📚 Library Management System (Java + MySQL)

A simple console-based Library Management System built using Core Java, JDBC, and MySQL.  
This project manages Books, Members, and Issued Books through a modular DAO + Service + UI structure.

## 🚀 Features

### 📘 Book Management
- Add new books  
- View all books  
- Update book quantity  
- Delete books  
- Auto-increment book IDs  

### 🧑‍🤝‍🧑 Member Management
- Add new members  
- View all members  
- Update member details  
- Delete members  
- Auto-increment member IDs  

### 📖 Issue Management
- Issue a book to a member  
- Return a book  
- View all issued books  
- Handles issue date and return date

# 📁 Project Structure

```text
src/
└── com/
    └── library/
        ├── dao/
        │   ├── imple/
        |   |   └── BookDAOImpl.java
        |   |   └── MemberDAOImpl.java
        |   |   └── IssueDAOImpl.java
        |   └── BookDAO.java
        │   └── MemberDAO.java
        |   └── IssueDAO.java
        │
        ├── model/
        │   └── Book
        |   └── IssuedBook
        |   └── Member
        │
        ├── service/
        │   └── LibraryService.java
        │
        ├── util/
        │   └── DBConnection
        │
        └── main/
            └── App.java
```

## 🗄️ Database Schema (MySQL)

### Table: books
- book_id (INT, PK, Auto Increment)  
- title (VARCHAR)  
- author (VARCHAR)  
- quantity (INT)  

### Table: members
- member_id (INT, PK, Auto Increment)  
- name (VARCHAR)  
- email (VARCHAR)  

### Table: issued_books
- issue_id (INT, PK, Auto Increment)  
- book_id (INT, FK → books.book_id)  
- member_id (INT, FK → members.member_id)  
- issue_date (DATE)  
- return_date (DATE)  

## 🛠️ Technologies Used
- Java (JDK 8+)  
- MySQL 8  
- JDBC  
- VS Code  
