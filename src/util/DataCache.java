package util;

import java.util.List;

/**
 * Simple cache for students/attendance to avoid repeated file reads.
 * Uses raw Lists - no model dependency.
 */
public class DataCache {
    private static List students;
    private static List attendance;

    public static List getStudents() {
        if (students == null) {
            students = FileHandler.loadStudents();
        }
        return students;
    }

    public static void updateStudents(List newStudents) {
        students = newStudents;
        FileHandler.saveStudents(newStudents);
    }

    public static List getAttendance() {
        if (attendance == null) {
            attendance = FileHandler.loadAttendance();
        }
        return attendance;
    }

    public static void updateAttendance(List newAttendance) {
        attendance = newAttendance;
        FileHandler.saveAttendance(newAttendance);
    }

    public static void invalidate() {
        students = null;
        attendance = null;
    }
}
