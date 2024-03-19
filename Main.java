import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        StudentManager studentDAO = new StudentManager();

        // Testing retrieve all students
        System.out.println("Retrieving all students...");
        studentDAO.getAllStudents();

        // Add Students test
        studentDAO.addStudent("Igor", "Tascu", "igortascu@cmail.carleton.ca", Date.valueOf("2023-01-01"));
        System.out.println("Updated Table: ");
        studentDAO.getAllStudents();

        // Test update email
        System.out.println("Updating student email...");
        studentDAO.updateStudentEmail(19, "janeUpdated@gmail.com");
        System.out.println("Updated Table: ");
        studentDAO.getAllStudents();

        // Test delete student
        System.out.println("Deleting a student...");
        studentDAO.deleteStudent(20);
        System.out.println("Updated Table: ");
        studentDAO.getAllStudents();
    }
}