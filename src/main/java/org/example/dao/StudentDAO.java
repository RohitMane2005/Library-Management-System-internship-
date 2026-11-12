package org.example.dao;

import org.example.db.DatabaseConnection;
import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getPhone());

            stmt.executeUpdate();
            System.out.println("Success: Student added successfully with ID: " + student.getStudentId());
        } catch (SQLException e) {
            System.out.println("Error: Failed to add student - " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = mapResultSetToStudent(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve students - " + e.getMessage());
        }

        return students;
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStudent(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve student - " + e.getMessage());
        }

        return null;
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, phone = ? WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setInt(4, student.getStudentId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success: Student details updated successfully for ID: " + student.getStudentId());
            } else {
                System.out.println("Error: Student with ID " + student.getStudentId() + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to update student - " + e.getMessage());
        }
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success: Student with ID " + studentId + " has been removed successfully.");
            } else {
                System.out.println("Error: Student with ID " + studentId + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to delete student - " + e.getMessage());
        }
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone")
        );
    }
}
