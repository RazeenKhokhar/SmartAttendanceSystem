package ui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Smart Attendance System");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Color primary = new Color(41, 128, 185);
        Color lightBg = new Color(248, 249, 250);
        Color accent = new Color(52, 168, 83);
        getContentPane().setBackground(lightBg);

        // Header
        JLabel header = new JLabel("Smart Attendance Dashboard", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(primary);
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabs.setBackground(Color.WHITE);
        tabs.setForeground(primary);
        UIManager.put("TabbedPane.selected", accent);
        tabs.addTab("Students", new StudentPanel());
        tabs.addTab("Attendance", new AttendancePanel());
        tabs.addTab("Reports", new ReportPanel());

        add(tabs, BorderLayout.CENTER);

        // Status bar
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusLabel.setForeground(Color.GRAY);
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(lightBg);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel statusLabel;

    public void setStatus(String msg) {
        statusLabel.setText(msg);
    }
}
