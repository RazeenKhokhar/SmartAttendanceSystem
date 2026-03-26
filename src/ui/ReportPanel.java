package ui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Attendance;
import model.Student;
import util.FileHandler;

public class ReportPanel extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private Color primary = new Color(41, 128, 185);
    private Color accent = new Color(52, 168, 83);
    private Color bgColor = new Color(248, 249, 250);

    public ReportPanel() {
        setLayout(new BorderLayout());
        setBackground(bgColor);

        // Styled top panel
        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(bgColor);
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton refreshBtn = new JButton("🔄 Refresh Report");
        refreshBtn.setBackground(primary);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        refreshBtn.addActionListener(e -> loadReport());
        top.add(refreshBtn);

        JButton exportBtn = new JButton("📤 Export CSV");
        exportBtn.setBackground(accent);
        exportBtn.setForeground(Color.WHITE);
        exportBtn.setFocusPainted(false);
        exportBtn.setBorderPainted(false);
        exportBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exportBtn.addActionListener(e -> exportCSV());
        top.add(exportBtn);

        add(top, BorderLayout.NORTH);

        // Styled table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Total", "Attended", "%"}, 0);
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
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadReport();
    }

    private void loadReport() {
        model.setRowCount(0);
        java.util.List<Student> students = FileHandler.loadStudents();
        java.util.List<Attendance> attendance = FileHandler.loadAttendance();

        for (Student s : students) {
            long total = attendance.stream().filter(a -> a.getStudentId().equals(s.getId())).count();
            long present = attendance.stream().filter(a -> a.getStudentId().equals(s.getId()) && "Present".equalsIgnoreCase(a.getStatus())).count();
            double pct = total > 0 ? (100.0 * present / total) : 0;
            model.addRow(new Object[]{s.getId(), s.getName(), total, present, String.format("%.1f%%", pct)});
        }
    }

    private void exportCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("report.csv"))) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                pw.print(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) pw.print(",");
            }
            pw.println();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    pw.print(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) pw.print(",");
                }
                pw.println();
            }
            JOptionPane.showMessageDialog(this, "report.csv exported successfully! ✅", "Exported", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Export error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

