/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public class Employee {

    // Employee information
    private String employeeNumber;
    private String employeeName;
    private String position;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String sssNumber;
    private String philHealthNumber;
    private String tinNumber;
    private String pagIBIGNumber;
    private double ratePerDay;

    // Constructor for the existing OOP implementation
    public Employee(String employeeNumber,
                    String employeeName,
                    String position,
                    double ratePerDay) {

        this(employeeNumber, employeeName, position,
                "", "", "", "", "", "", "",
                ratePerDay);
    }

    // Constructor for the full employee information
    public Employee(String employeeNumber,
                    String employeeName,
                    String position,
                    String birthday,
                    String phoneNumber,
                    String address,
                    String sssNumber,
                    String philHealthNumber,
                    String tinNumber,
                    String pagIBIGNumber,
                    double ratePerDay) {

    // Validate employee number
    if (employeeNumber == null || employeeNumber.trim().isEmpty()) {
        throw new IllegalArgumentException(
                "Employee number is required."
        );
    }


        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.position = position;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tinNumber = tinNumber;
        this.pagIBIGNumber = pagIBIGNumber;

        setRatePerDay(ratePerDay);
    }

    // Getters
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public String getPagIBIGNumber() {
        return pagIBIGNumber;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }

 // Setter with validation
    public void setRatePerDay(double ratePerDay) {
        if (!Double.isFinite(ratePerDay) || ratePerDay < 0) {
            throw new IllegalArgumentException(
                    "Rate per day must be a valid, non-negative number."
            );
        }

        this.ratePerDay = ratePerDay;
    }
    public String getEmployeeType() {
    return "Employee";
    }
    
}