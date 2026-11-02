/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public class Attendance {

    private String employeeNumber;
    private int daysWorked;

   public Attendance(String employeeNumber, int daysWorked) {

    // Validate employee number
    if (employeeNumber == null || employeeNumber.trim().isEmpty()) {
        throw new IllegalArgumentException(
                "Employee number is required."
        );
    }

    this.employeeNumber = employeeNumber;
    setDaysWorked(daysWorked);
}

    // Getters
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    // Validate days worked
    public void setDaysWorked(int daysWorked) {
        if (daysWorked < 0) {
            throw new IllegalArgumentException(
                    "Days worked cannot be negative."
            );
        }

        this.daysWorked = daysWorked;
    }
}