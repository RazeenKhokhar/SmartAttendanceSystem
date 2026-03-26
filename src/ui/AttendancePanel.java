package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Attendance;
import model.Student;
import util.FileHandler;

public class AttendancePanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private JTextField dateField;
    private Color primary = new Color(41, 128, 185);
    private Color accent = new Color(52, 168, 83);
    private Color bgColor = new Color(248, 249, 250);
    private Color deleteColor = new Color(220, 53, 69);

    public AttendancePanel() {
        setLayout(new BorderLayout());
        setBackground(bgColor);

        // Styled top panel
        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(bgColor);
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel dateLabel = new JLabel("📅 Date:");
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setForeground(primary);
        top.add(dateLabel);

        dateField = new JTextField(LocalDate.now().toString(), 12);
        dateField.setBorder(BorderFactory.createLineBorder(primary, 2));
        dateField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        top.add(dateField);

        JButton saveBtn = new JButton("💾 Save Attendance");
        saveBtn.setBackground(accent);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorderPainted(false);
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        saveBtn.addActionListener(this::saveAttendance);
        top.add(saveBtn);

        add(top, BorderLayout.NORTH);

        // Styled table like StudentPanel
        model = new DefaultTableModel(new String[]{"ID", "Name", "Status"}, 0);
        table = new JTable(model);
        table.setSelectionBackground(primary);
        table.setRowHeight(35);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setBackground(primary);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Status combo editor
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"Present", "Absent"})));

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadStudents();
    }

    private void loadStudents() {
        model.setRowCount(0);
        List<Student> students = FileHandler.loadStudents();
        students.forEach(s -> model.addRow(new Object[]{s.getId(), s.getName(), "Present"}));
    }

    private void saveAttendance(ActionEvent e) {
        String date = dateField.getText().trim();
        List<Attendance> records = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String id = (String) model.getValueAt(i, 0);
            String status = (String) model.getValueAt(i, 2);
            records.add(new Attendance(date, id, status));
        }
        FileHandler.saveAttendanceList(records);
        JOptionPane.showMessageDialog(this, "Attendance saved successfully! ✅", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

