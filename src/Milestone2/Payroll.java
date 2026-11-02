/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public class Payroll {

    private double grossPay;
    private double sss;
    private double philHealth;
    private double pagIBIG;
    private double withholdingTax;
    private double totalDeductions;
    private double netPay;

    // Calculate salary using Employee and Attendance objects
    public void calculate(Employee employee, Attendance attendance) {

        if (employee == null || attendance == null) {
            throw new IllegalArgumentException(
                    "Employee and attendance are required."
            );
        }

        if (employee.getEmployeeNumber() == null
        || attendance.getEmployeeNumber() == null
        || !employee.getEmployeeNumber().equals(
                attendance.getEmployeeNumber())) {
             throw new IllegalArgumentException(
            "Employee number and attendance do not match."
    );
}

        // Gross pay
        grossPay = employee.getRatePerDay()
                * attendance.getDaysWorked();

        // Deductions based on the original CP2 formulas
        sss = grossPay * 0.05;
        philHealth = grossPay * 0.03;
        pagIBIG = 100.00;
        withholdingTax = grossPay * 0.10;

        totalDeductions = sss + philHealth
                + pagIBIG + withholdingTax;

        netPay = grossPay - totalDeductions;
    }

    // Getters
    public double getGrossPay() {
        return grossPay;
    }

    public double getSss() {
        return sss;
    }

    public double getPhilHealth() {
        return philHealth;
    }

    public double getPagIBIG() {
        return pagIBIG;
    }

    public double getWithholdingTax() {
        return withholdingTax;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public double getNetPay() {
        return netPay;
    }
}