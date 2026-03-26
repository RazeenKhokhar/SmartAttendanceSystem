package util;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Attendance;
import model.Student;

/**
 * Utility class to handle reading from and writing to CSV files.
 * Updated for multi-class support (ID,Name,Class).
 */
public class FileHandler {
    private static final String STUDENT_FILE = "students.csv";
    private static final String ATTENDANCE_FILE = "attendance.csv";

    /**
     * Checks if files exist, if not, creates them and adds dummy data.
     */
    public static void initializeFiles() {
        try {
            File sFile = new File(STUDENT_FILE);
            if (!sFile.exists()) {
                sFile.createNewFile();
                // Add dummy students with classes
                try (PrintWriter writer = new PrintWriter(new FileWriter(sFile))) {
                    writer.println("ID,Name,Class");
                    writer.println("101,John Doe,CS101");
                    writer.println("102,Jane Smith,CS101");
                    writer.println("103,Alice Johnson,Math202");
                }
            }

            File aFile = new File(ATTENDANCE_FILE);
            if (!aFile.exists()) {
                aFile.createNewFile();
                // Add dummy attendance for yesterday
                String yesterday = LocalDate.now().minusDays(1).toString();
                try (PrintWriter writer = new PrintWriter(new FileWriter(aFile))) {
                    writer.println("Date,StudentID,Status");
                    writer.println(yesterday + ",101,Present");
                    writer.println(yesterday + ",102,Absent");
                    writer.println(yesterday + ",103,Present");
                }
            }
        } catch (IOException e) {
            System.out.println("Error initializing files: " + e.getMessage());
        }
    }

    /**
     * Reads all students from the CSV file (supports ID,Name,Class).
     */
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String cls = parts.length > 2 ? parts[2] : "";
                    students.add(new Student(parts[0], parts[1], cls));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    /**
     * Appends a single new student to the CSV file.
     */
    public static void saveStudent(Student student) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE, true))) {
            writer.println(student.toString());
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    /**
     * Reads all attendance records from the CSV file.
     */
    public static List<Attendance> loadAttendance() {
        List<Attendance> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ATTENDANCE_FILE))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    records.add(new Attendance(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading attendance: " + e.getMessage());
        }
        return records;
    }

    /**
     * Appends a list of attendance records to the CSV file.
     */
    public static void saveAttendanceList(List<Attendance> records) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ATTENDANCE_FILE, true))) {
            for (Attendance att : records) {
                writer.println(att.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving attendance list: " + e.getMessage());
        }
    }
}

