/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public class PayrollEmployee extends Employee {

    // Constructor for basic employee information
    public PayrollEmployee(
            String employeeNumber,
            String employeeName,
            String position,
            double ratePerDay) {

        super(employeeNumber, employeeName, position, ratePerDay);
    }

    // Constructor for full employee information
    public PayrollEmployee(
            String employeeNumber,
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

        super(
            employeeNumber,
            employeeName,
            position,
            birthday,
            phoneNumber,
            address,
            sssNumber,
            philHealthNumber,
            tinNumber,
            pagIBIGNumber,
            ratePerDay
        );
    }

    @Override
    public String getEmployeeType() {
        return "Payroll Employee";
    }
}