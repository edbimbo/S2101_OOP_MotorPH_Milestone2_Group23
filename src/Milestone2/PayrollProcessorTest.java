/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public class PayrollProcessorTest {

    public static void main(String[] args) {

        PayrollProcessor processor = new PayrollProcessor();

        Employee employee = new PayrollEmployee(
                "EMP001", "Test Employee", "Staff", 1000.00);

        Attendance attendance = new Attendance("EMP001", 5);

        // Test 1: Null employee
        try {
            processor.processPayroll(null, attendance);
            System.out.println("FAIL: Null employee was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Null employee was rejected.");
        }

        // Test 2: Null attendance
        try {
            processor.processPayroll(employee, null);
            System.out.println("FAIL: Null attendance was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Null attendance was rejected.");
        }

        // Test 3: Mismatched employee numbers
        try {
            Attendance wrongAttendance =
                    new Attendance("EMP002", 5);

            processor.processPayroll(employee, wrongAttendance);
            System.out.println("FAIL: Mismatched records were accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Mismatched records were rejected.");
        }

        // Test 4: Payroll calculation
        Payroll payroll =
                processor.processPayroll(employee, attendance);

        if (payroll.getGrossPay() == 5000.00
                && payroll.getSss() == 250.00
                && payroll.getPhilHealth() == 150.00
                && payroll.getPagIBIG() == 100.00
                && payroll.getWithholdingTax() == 500.00
                && payroll.getTotalDeductions() == 1000.00
                && payroll.getNetPay() == 4000.00) {

            System.out.println("PASS: Payroll calculations are correct.");
        } else {
            System.out.println("FAIL: Payroll calculations are incorrect.");
        }

        // Test 5: Runtime polymorphism
        if (employee.getEmployeeType().equals("Payroll Employee")) {
            System.out.println("PASS: Overridden employee type was used.");
        } else {
            System.out.println("FAIL: Employee type override was not used.");
        }
    }
}