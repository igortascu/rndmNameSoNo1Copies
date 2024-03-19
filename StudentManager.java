import java.sql.*;

public class StudentManager {
    private String url = "jdbc:postgresql://localhost:5432/Assignment3";
    private String user = "postgres";
    private String password = "<PASSWORD>>";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    // Get all students function
    public void getAllStudents() {
        String SQL = "SELECT * FROM students";

        try (Connection conn = connect();
             // create the statement and execute it
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(SQL)) {

            // print results
            while (rs.next()) {
                System.out.print(rs.getInt("student_id") + " ");
                System.out.print(rs.getString("first_name") + " ");
                System.out.print(rs.getString("last_name") + " ");
                System.out.print(rs.getString("email") + " ");
                System.out.println(rs.getDate("enrollment_date"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Add student function
    public void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        String SQL = "INSERT INTO students(first_name, last_name, email, enrollment_date) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {

            // set statement parameters
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, enrollment_date);
            int outputRows = statement.executeUpdate();

            if (outputRows > 0) {
                System.out.println("New student successfully inserted!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Update student email function
    public void updateStudentEmail(int student_id, String new_email) {
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);

            int outputRows = pstmt.executeUpdate();
            if (outputRows > 0) {
                System.out.println("Student email updated successfully!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Delete student function
    public void deleteStudent(int student_id) {
        String SQL = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, student_id);

            int outputRows = pstmt.executeUpdate();
            if (outputRows > 0) {
                System.out.println("Student successfully deleted!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

