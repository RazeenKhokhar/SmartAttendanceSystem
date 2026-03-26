package model;

/**
 * Model class representing a Student.
 * Enhanced with class/section for multi-class support.
 */
public class Student {
    private String id;
    private String name;
    private String classSection;  // NEW: e.g., "CS101", "Math202"

    // Constructors
    public Student(String id, String name) {
        this(id, name, "");
    }

    public Student(String id, String name, String classSection) {
        this.id = id;
        this.name = name;
        this.classSection = classSection;
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassSection() { return classSection; }
    public void setClassSection(String classSection) { this.classSection = classSection; }

    /**
     * Converts to CSV: ID,Name,ClassSection
     */
    @Override
    public String toString() {
        return id + "," + name + "," + classSection;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id.equals(student.id);
    }
}

