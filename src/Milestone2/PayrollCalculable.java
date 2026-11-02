/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
public interface PayrollCalculable {

    Payroll processPayroll(
            Employee employee,
            Attendance attendance
    );
}