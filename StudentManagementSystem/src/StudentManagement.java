import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentManagement extends JFrame implements ActionListener {
    JLabel jtitle;
    JLabel studentName, studentID, studentGrade, dobLabel, genderLabel, contactLabel, emailLabel;
    JTextField jstudentName, jstudentID, jstudentGrade, dobField, contactField, emailField, searchField;
    JRadioButton maleRadio, femaleRadio;
    ButtonGroup genderGroup;
    JButton addStudent, reset, deleteRecord, searchButton, updateStudent, viewDetails;
    JTable studentTable;
    DefaultTableModel tableModel;

    public StudentManagement() {
        setTitle("Student Management System");
        setLayout(null);
        setSize(1000, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(221, 168, 255)); 

        jtitle = new JLabel("STUDENT MANAGEMENT SYSTEM");
        jtitle.setBounds(250, 10, 700, 50);
        jtitle.setFont(new Font("Arial", Font.BOLD, 30)); 

        studentName = new JLabel("Student Name");
        studentName.setBounds(50, 80, 150, 30);

        studentID = new JLabel("Student ID");
        studentID.setBounds(50, 120, 150, 30);

        studentGrade = new JLabel("Student Grade");
        studentGrade.setBounds(50, 160, 150, 30);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(50, 200, 150, 30);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 240, 150, 30);

        contactLabel = new JLabel("Contact Number");
        contactLabel.setBounds(50, 280, 150, 30);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 320, 150, 30);

        jstudentName = new JTextField();
        jstudentName.setBounds(200, 80, 200, 30);

        jstudentID = new JTextField();
        jstudentID.setBounds(200, 120, 200, 30);

        jstudentGrade = new JTextField();
        jstudentGrade.setBounds(200, 160, 200, 30); 

        dobField = new JTextField();
        dobField.setBounds(200, 200, 200, 30);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(200, 240, 80, 30);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(290, 240, 100, 30);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        contactField = new JTextField();
        contactField.setBounds(200, 280, 200, 30); 

        emailField = new JTextField();
        emailField.setBounds(200, 320, 200, 30);

        addStudent = new JButton("Add Student");
        addStudent.setBounds(650, 150, 150, 30);

        reset = new JButton("Reset");
        reset.setBounds(650, 200, 150, 30);

        deleteRecord = new JButton("Delete Record");
        deleteRecord.setBounds(650, 250, 150, 30);

        updateStudent = new JButton("Update Student");
        updateStudent.setBounds(650, 300, 150, 30);

        viewDetails = new JButton("View Student Details");
        viewDetails.setBounds(650, 350, 150, 30);

        searchField = new JTextField();
        searchField.setBounds(50, 360, 300, 30);

        searchButton = new JButton("Search by ID");
        searchButton.setBounds(360, 360, 150, 30);

        add(jtitle);
        add(studentName);
        add(studentID);
        add(studentGrade);
        add(dobLabel);
        add(genderLabel);
        add(contactLabel);
        add(emailLabel);
        add(jstudentName);
        add(jstudentID);
        add(jstudentGrade);
        add(dobField);
        add(maleRadio);
        add(femaleRadio);
        add(contactField);
        add(emailField);
        add(addStudent);
        add(reset);
        add(deleteRecord);
        add(updateStudent);
        add(viewDetails);
        add(searchField);
        add(searchButton);

        String[] columnNames = {"Student Name", "Student ID", "Student Grade", "Date of Birth", "Gender", "Contact Number", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);

        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 400, 860, 150);
        add(scrollPane);

        addStudent.addActionListener(this);
        reset.addActionListener(this);
        deleteRecord.addActionListener(this);
        updateStudent.addActionListener(this);
        viewDetails.addActionListener(this);
        searchButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            String name = jstudentName.getText();
            String id = jstudentID.getText();
            String grade = jstudentGrade.getText();
            String dob = dobField.getText();
            String contact = contactField.getText();
            String email = emailField.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";

            if (name.isEmpty() || id.isEmpty() || grade.isEmpty() || dob.isEmpty() || contact.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] data = {name, id, grade, dob, gender, contact, email};
                tableModel.addRow(data);

                jstudentName.setText("");
                jstudentID.setText("");
                jstudentGrade.setText("");
                dobField.setText("");
                genderGroup.clearSelection();
                contactField.setText("");
                emailField.setText("");
            }
        }

        if (e.getSource() == reset) {
            jstudentName.setText("");
            jstudentID.setText("");
            jstudentGrade.setText("");
            dobField.setText("");
            genderGroup.clearSelection();
            contactField.setText("");
            emailField.setText("");
        }

        if (e.getSource() == deleteRecord) {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == updateStudent) {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                jstudentName.setText((String) tableModel.getValueAt(selectedRow, 0));
                jstudentID.setText((String) tableModel.getValueAt(selectedRow, 1));
                jstudentGrade.setText((String) tableModel.getValueAt(selectedRow, 2));
                dobField.setText((String) tableModel.getValueAt(selectedRow, 3));
                String gender = (String) tableModel.getValueAt(selectedRow, 4);
                if (gender.equals("Male")) {
                    maleRadio.setSelected(true);
                } else {
                    femaleRadio.setSelected(true);
                }
                contactField.setText((String) tableModel.getValueAt(selectedRow, 5));
                emailField.setText((String) tableModel.getValueAt(selectedRow, 6));

                addStudent.setText("Save Changes");
                addStudent.setActionCommand("saveChanges");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == viewDetails) {
            JDialog dialog = new JDialog(this, "Student Details", true);
            dialog.setSize(900, 400);
            dialog.setLayout(new BorderLayout());

            JTable viewTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(viewTable);
            dialog.add(scrollPane, BorderLayout.CENTER);

            dialog.setVisible(true);
        }

        if (e.getSource() == searchButton) {
            String searchId = searchField.getText();
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                if (tableModel.getValueAt(row, 1).equals(searchId)) {
                    studentTable.setRowSelectionInterval(row, row);
                    studentTable.setSelectionBackground(Color.YELLOW);
                    studentTable.setSelectionForeground(Color.BLACK);
                    break;
                }
            }
        }

        if ("saveChanges".equals(e.getActionCommand())) {
            int selectedRow = studentTable.getSelectedRow();
            tableModel.setValueAt(jstudentName.getText(), selectedRow, 0);
            tableModel.setValueAt(jstudentID.getText(), selectedRow, 1);
            tableModel.setValueAt(jstudentGrade.getText(), selectedRow, 2);
            tableModel.setValueAt(dobField.getText(), selectedRow, 3);
            tableModel.setValueAt(maleRadio.isSelected() ? "Male" : "Female", selectedRow, 4);
            tableModel.setValueAt(contactField.getText(), selectedRow, 5);
            tableModel.setValueAt(emailField.getText(), selectedRow, 6);

            addStudent.setText("Add Student");
            addStudent.setActionCommand("addStudent");

            jstudentName.setText("");
            jstudentID.setText("");
            jstudentGrade.setText("");
            dobField.setText("");
            genderGroup.clearSelection();
            contactField.setText("");
            emailField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagement();
        });
    }
}
