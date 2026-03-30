package ui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Student;
import util.FileHandler;

public class StudentPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField idField, nameField, classField;
    private Color primary = new Color(41, 128, 185);
    private Color accent = new Color(52, 168, 83);
    private Color deleteColor = new Color(220, 53, 69);
    private Color bgColor = new Color(248, 249, 250);

    public StudentPanel() {
        setLayout(new BorderLayout());

        // Beautiful inputs
        JPanel inputs = new JPanel(new FlowLayout());
        inputs.setBackground(bgColor);

        inputs.add(new JLabel("ID:"));
        idField = new JTextField(10);
        idField.setBorder(BorderFactory.createLineBorder(primary, 2));
        inputs.add(idField);

        inputs.add(new JLabel("Name:"));
        nameField = new JTextField(12);
        nameField.setBorder(BorderFactory.createLineBorder(primary, 2));
        inputs.add(nameField);

        inputs.add(new JLabel("Class:"));
        classField = new JTextField(10);
        classField.setBorder(BorderFactory.createLineBorder(primary, 2));
        inputs.add(classField);

        JButton addBtn = new JButton("+ Add Student");
        addBtn.setBackground(accent);
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setBorderPainted(false);
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addBtn.addActionListener(e -> addStudent());
        inputs.add(addBtn);

        add(inputs, BorderLayout.NORTH);

        // Stylish table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Class"}, 0);
        table = new JTable(model);
        table.setSelectionBackground(primary);
        table.setRowHeight(35);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setRowSelectionAllowed(true);
        table.getTableHeader().setBackground(primary);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Attractive buttons
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBackground(bgColor);
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(primary);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.addActionListener(e -> loadTable());
        buttons.add(refreshBtn);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBackground(deleteColor);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorderPainted(false);
        deleteBtn.addActionListener(e -> deleteSelected());
        buttons.add(deleteBtn);

        add(buttons, BorderLayout.SOUTH);

        loadTable();
    }

    private void loadTable() {
        model.setRowCount(0);
        List<Student> students = FileHandler.loadStudents();
        students.forEach(s -> model.addRow(new Object[]{s.getId(), s.getName(), s.getClassSection()}));
    }

    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String cls = classField.getText().trim();
        if (!id.isEmpty() && !name.isEmpty()) {
            List<Student> existing = FileHandler.loadStudents();
            if (existing.stream().noneMatch(s -> s.getId().equals(id))) {
                FileHandler.saveStudent(new Student(id, name, cls));
                idField.setText("");
                nameField.setText("");
                classField.setText("");
                loadTable();
                JOptionPane.showMessageDialog(this, "Student added successfully! ✨", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student ID already exists! Please use unique ID.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill ID and Name.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete selected student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                List<Student> students = FileHandler.loadStudents();
                Student toDelete = students.get(row);
                students.removeIf(s -> s.getId().equals(toDelete.getId()));
                FileHandler.saveStudents(students);
                loadTable();
                JOptionPane.showMessageDialog(this, "Student deleted successfully! ✅", "Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
}

