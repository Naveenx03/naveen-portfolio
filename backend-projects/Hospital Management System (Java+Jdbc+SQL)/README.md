# 🏥 Hospital Management System (Java + MySQL)

A simple console-based Hospital Management System built using Core Java, JDBC, and MySQL.  
This project allows management of Patients, Doctors, and Appointments through a clean, menu-driven console interface.

## 🚀 Features

### 👨‍⚕️ Doctor Management
- Add new doctor  
- View all doctors  
- Auto-increment doctor IDs  

### 🧑‍🦰 Patient Management
- Add new patient  
- View all patients  
- Auto-increment patient IDs  

### 📅 Appointment Management
- Create appointments  
- View all appointments  
- Appointment linked using foreign keys to:
  - patients  
  - doctors  


## 🗄️ Database Schema (MySQL)

### Table: patients
- id (INT, PK, Auto Increment)  
- name (VARCHAR)  
- age (INT)  
- gender (VARCHAR)  

### Table: doctors
- id (INT, PK, Auto Increment)  
- name (VARCHAR)  
- specialization (VARCHAR)  

### Table: appointments
- id (INT, PK, Auto Increment)  
- patient_id (INT, FK → patients.id)  
- doctor_id (INT, FK → doctors.id)  
- appointment_date (DATE)  

## 🛠️ Technologies Used
- Java (JDK 8+)  
- MySQL 8  
- JDBC  
- VS Code  
