/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Milestone2;

/**
 *
 * @author paopa
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainGUI extends JFrame {

    private final String[] columns = {
        "Employee Name", "Employee Number", "Position", "Birthday",
        "Phone Number", "Address", "SSS Number", "PhilHealth Number",
        "TIN Number", "Pag-IBIG Number", "Rate Per Day", "Days Worked"
    };

    private final JTextField[] fields = new JTextField[12];
    private final JTable employeeTable;
    private final DefaultTableModel model;

    private final EmployeeDataHandler dataHandler =
            new EmployeeDataHandler();

    private final PayrollCalculable payrollProcessor =
        new PayrollProcessor();

    public MainGUI() {
        setTitle("MotorPH Payroll System");
        setSize(1200, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                "Employee Information"));

        for (int i = 0; i < columns.length; i++) {
            inputPanel.add(new JLabel(columns[i] + ":"));
            fields[i] = new JTextField();
            inputPanel.add(fields[i]);
        }

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(model);
        employeeTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()
                    && employeeTable.getSelectedRow() >= 0) {
                fillFieldsFromSelectedRow();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton clearButton = new JButton("Clear");
        JButton computeButton = new JButton("Compute Salary");
        JButton summaryButton = new JButton("Generate Summary");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(computeButton);
        buttonPanel.add(summaryButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        clearButton.addActionListener(e -> clearFields());
        computeButton.addActionListener(e -> computeSalary());
        summaryButton.addActionListener(e -> generateSummary());

        loadTable();
    }

    private String[] getFieldValues() {
        String[] values = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            values[i] = fields[i].getText().trim();
        }

        return values;
    }

    private boolean validateFields(String[] v) {
        for (int i = 0; i < v.length; i++) {
            if (v[i].isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, columns[i] + " is required.");
                return false;
            }

            if (v[i].contains(",")) {
                JOptionPane.showMessageDialog(
                        this, "Commas are not allowed in employee fields.");
                return false;
            }
        }

        try {
            double rate = Double.parseDouble(v[10]);
            int days = Integer.parseInt(v[11]);

            if (rate < 0 || days < 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Rate per day must be a non-negative number, "
                    + "and days worked must be a non-negative whole number.");
            return false;
        }

        return true;
    }

    private Employee createEmployee(String[] v) {
        return new PayrollEmployee(
                v[1], v[0], v[2], v[3], v[4], v[5],
                v[6], v[7], v[8], v[9],
                Double.parseDouble(v[10]));
    }

    private Attendance createAttendance(String[] v) {
        return new Attendance(
                v[1], Integer.parseInt(v[11]));
    }

    private void addEmployee() {
        String[] v = getFieldValues();

        if (!validateFields(v)) {
            return;
        }

        if (dataHandler.employeeExists(v[1])) {
            JOptionPane.showMessageDialog(
                    this, "That employee number already exists.");
            return;
        }

        dataHandler.saveEmployee(
                v[0], v[1], v[2], v[4], v[3], v[5],
                v[6], v[7], v[8], v[9], v[10], v[11]);

        loadTable();
        clearFields();

        JOptionPane.showMessageDialog(
                this, "Employee saved.");
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(
                    this, "Please select an employee to update.");
            return;
        }

        String[] v = getFieldValues();

        if (!validateFields(v)) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Save changes to this employee?",
                "Confirm Update",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        dataHandler.updateEmployee(
                selectedRow,
                v[0], v[1], v[2], v[3], v[4], v[5],
                v[6], v[7], v[8], v[9], v[10], v[11]);

        loadTable();
        clearFields();

        JOptionPane.showMessageDialog(
                this, "Employee update requested.");
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(
                    this, "Please select an employee to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        dataHandler.deleteEmployee(selectedRow);
        loadTable();
        clearFields();
    }

    private void computeSalary() {
        String[] v = getFieldValues();

        if (v[1].isEmpty() || v[0].isEmpty()
                || v[2].isEmpty() || v[10].isEmpty()
                || v[11].isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter the employee name, number, position, "
                    + "rate per day, and days worked.");
            return;
        }

        try {
            double rate = Double.parseDouble(v[10]);
            int days = Integer.parseInt(v[11]);

            if (rate < 0 || days < 0) {
                throw new NumberFormatException();
            }

            Employee employee = createEmployee(v);
            Attendance attendance = createAttendance(v);

            Payroll payroll = payrollProcessor.processPayroll(
                    employee, attendance);

            String result = String.format(
                    "Employee: %s%n"
                    + "Employee Number: %s%n%n"
                    + "Gross Pay: %.2f%n"
                    + "SSS: %.2f%n"
                    + "PhilHealth: %.2f%n"
                    + "Pag-IBIG: %.2f%n"
                    + "Withholding Tax: %.2f%n"
                    + "Total Deductions: %.2f%n"
                    + "Net Pay: %.2f",
                    employee.getEmployeeName(),
                    employee.getEmployeeNumber(),
                    payroll.getGrossPay(),
                    payroll.getSss(),
                    payroll.getPhilHealth(),
                    payroll.getPagIBIG(),
                    payroll.getWithholdingTax(),
                    payroll.getTotalDeductions(),
                    payroll.getNetPay());

            JOptionPane.showMessageDialog(
                    this, result, "Payroll Result",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Enter a valid rate and number of days worked.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage());
        }
    }

    private void generateSummary() {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this, "There are no employee records to summarize.");
            return;
        }

        double totalGross = 0;
        double totalDeductions = 0;
        double totalNet = 0;

        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                String[] v = new String[12];

                for (int j = 0; j < 12; j++) {
                    v[j] = model.getValueAt(i, j).toString();
                }

                Employee employee = createEmployee(v);
                Attendance attendance = createAttendance(v);

                Payroll payroll = payrollProcessor.processPayroll(
                        employee, attendance);

                totalGross += payroll.getGrossPay();
                totalDeductions += payroll.getTotalDeductions();
                totalNet += payroll.getNetPay();
            }

            String result = String.format(
                    "Number of Employees: %d%n"
                    + "Total Gross Pay: %.2f%n"
                    + "Total Deductions: %.2f%n"
                    + "Total Net Pay: %.2f%n"
                    + "Average Net Pay: %.2f",
                    model.getRowCount(),
                    totalGross,
                    totalDeductions,
                    totalNet,
                    totalNet / model.getRowCount());

            JOptionPane.showMessageDialog(
                    this, result, "Payroll Summary",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException ex) { 
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to generate summary: " + ex.getMessage());
        }
    }

    private void fillFieldsFromSelectedRow() {
        int row = employeeTable.getSelectedRow();

        if (row < 0) {
            return;
        }

        for (int i = 0; i < fields.length; i++) {
            fields[i].setText(
                    model.getValueAt(row, i).toString());
        }
    }

    private void clearFields() {
        employeeTable.clearSelection();

        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void loadTable() {
        dataHandler.loadEmployees(model);
    }
}