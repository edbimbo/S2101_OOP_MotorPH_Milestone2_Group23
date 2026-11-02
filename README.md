MotorPH Payroll System – OOP Milestone 2

Project Overview

The MotorPH Payroll System is a Java-based application developed as part of our Object-Oriented Programming (OOP) Milestone 2 project.

The project applies Object-Oriented Programming concepts to organize the payroll system into separate classes, making the code easier to understand, maintain, and manage.

The application allows users to manage employee records, process payroll, calculate salary deductions, and generate payroll summaries through a graphical user interface (GUI).

Technologies Used

Java

Apache NetBeans IDE

Java Swing (GUI)

CSV File Handling

GitHub

Main Features

Add Employee – Add new employee records.

Update Employee – Modify existing employee information.

Delete Employee – Remove employee records.

Clear Fields – Clear the input fields.

Compute Salary – Calculate gross pay, deductions, and net pay.

Generate Summary – Display payroll totals and employee summaries.

Input Validation – Reject invalid employee and payroll information.

CSV File Handling – Save and retrieve employee records.

Object-Oriented Programming Concepts

The project demonstrates the four fundamental OOP concepts:

1. Encapsulation

Employee and payroll information are stored using private fields. Getters and setters control access to the data.

2. Abstraction

The PayrollCalculable interface defines the payroll processing operation without exposing its implementation details.

3. Inheritance

The PayrollEmployee class extends the Employee class and inherits its properties and methods.

4. Polymorphism

The PayrollEmployee class overrides the getEmployeeType() method, allowing it to provide its own implementation.

Project Structure

OOP_Milestone2/
├── nbproject/
├── src/
│   └── Milestone2/
│       ├── Employee.java
│       ├── PayrollEmployee.java
│       ├── Attendance.java
│       ├── Payroll.java
│       ├── PayrollCalculable.java
│       ├── PayrollProcessor.java
│       ├── EmployeeDataHandler.java
│       ├── MainGUI.java
│       └── PayrollProcessorTest.java
├── build/
├── dist/
├── build.xml
├── manifest.mf
└── employees.csv

How to Run the Project

Open Apache NetBeans IDE.

Select File → Open Project.

Locate and select the OOP_Milestone2 project folder.

Open the project.

Run the application through NetBeans.

Testing

The project underwent functional and backend testing.

GUI Testing

Add Employee

Update Employee

Delete Employee

Clear Fields

Compute Salary

Generate Summary

Backend Testing

Null employee validation

Null attendance validation

Employee and attendance record matching

Payroll calculation verification

Polymorphism verification

Additional input validation tests were also performed for negative salary rates and negative attendance days.

Project Purpose

This project demonstrates how Object-Oriented Programming can be applied to an existing payroll system by separating responsibilities into different classes while allowing them to work together.

Project: MotorPH Payroll System
Milestone: Milestone 2 – Object-Oriented Programming
IDE: Apache NetBeans
