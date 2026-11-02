/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class EmployeeDataHandler {

    private final String FILE_NAME = "employees.csv";

    // Load all employees into the table
    public void loadEmployees(DefaultTableModel model) {
        model.setRowCount(0);

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                // Only load records with all 12 fields
                if (data.length == 12) {
                    model.addRow(data);
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error loading employees: " + e.getMessage()
            );
        }
    }

    // Save a new employee
    public void saveEmployee(
            String employeeName,
            String employeeNumber,
            String position,
            String phoneNumber,
            String birthday,
            String address,
            String SSSNumber,
            String PhilHealthNumber,
            String TINNumber,
            String PagIBIGNumber,
            String ratePerDay,
            String daysWorked) {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write(
                    employeeName + ","
                    + employeeNumber + ","
                    + position + ","
                    + birthday + ","
                    + phoneNumber + ","
                    + address + ","
                    + SSSNumber + ","
                    + PhilHealthNumber + ","
                    + TINNumber + ","
                    + PagIBIGNumber + ","
                    + ratePerDay + ","
                    + daysWorked
            );

            writer.newLine();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error saving employee: " + e.getMessage()
            );
        }
    }

    // Check whether an employee number already exists
    public boolean employeeExists(String employeeNumber) {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length == 12
                        && data[1].trim().equals(employeeNumber.trim())) {
                    return true;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error checking employee records: " + e.getMessage()
            );
        }

        return false;
    }

    // Update an employee using the selected table row
    public void updateEmployee(
            int row,
            String employeeName,
            String employeeNumber,
            String position,
            String birthday,
            String phoneNumber,
            String address,
            String SSSNumber,
            String PhilHealthNumber,
            String TINNumber,
            String PagIBIGNumber,
            String ratePerDay,
            String daysWorked) {

        ArrayList<String> employees = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                employees.add(line);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error reading employee records: " + e.getMessage()
            );
            return;
        }

        if (row < 0 || row >= employees.size()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please select a valid employee record."
            );
            return;
        }

        employees.set(
                row,
                employeeName + ","
                + employeeNumber + ","
                + position + ","
                + birthday + ","
                + phoneNumber + ","
                + address + ","
                + SSSNumber + ","
                + PhilHealthNumber + ","
                + TINNumber + ","
                + PagIBIGNumber + ","
                + ratePerDay + ","
                + daysWorked
        );

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String employee : employees) {
                writer.write(employee);
                writer.newLine();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error updating employee: " + e.getMessage()
            );
        }
    }

    // Delete an employee using the selected table row
    public void deleteEmployee(int row) {
        ArrayList<String> employees = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                employees.add(line);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error reading employee records: " + e.getMessage()
            );
            return;
        }

        if (row < 0 || row >= employees.size()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please select a valid employee record."
            );
            return;
        }

        employees.remove(row);

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (String employee : employees) {
                writer.write(employee);
                writer.newLine();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error deleting employee: " + e.getMessage()
            );
        }
    }
}