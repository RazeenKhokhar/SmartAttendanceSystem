package model;

/**
 * Model class representing an Attendance Record for a specific date.
 */
public class Attendance {
    private String date;
    private String studentId;
    private String status; // "Present" or "Absent"

    // Constructor
    public Attendance(String date, String studentId, String status) {
        this.date = date;
        this.studentId = studentId;
        this.status = status;
    }

    // Getters
    public String getDate() { return date; }
    public String getStudentId() { return studentId; }
    public String getStatus() { return status; }

    // Used for converting object to CSV format string
    public String toCSV() {
        return date + "," + studentId + "," + status;
    }
}