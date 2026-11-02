/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public class PayrollProcessor implements PayrollCalculable {

    // Process payroll for an employee
    @Override
    public Payroll processPayroll(
            Employee employee,
            Attendance attendance) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee information is required."
            );
        }

        if (attendance == null) {
            throw new IllegalArgumentException(
                    "Attendance information is required."
            );
        }

        if (!employee.getEmployeeNumber().equals(
                attendance.getEmployeeNumber())) {
            throw new IllegalArgumentException(
                    "Employee number and attendance do not match."
            );
        }

        // Demonstrate runtime polymorphism
        System.out.println(
                "Processing payroll for: "
                + employee.getEmployeeType()
        );

        Payroll payroll = new Payroll();
        payroll.calculate(employee, attendance);

        return payroll;
    }
}