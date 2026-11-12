package org.example.dao;

import org.example.db.DatabaseConnection;
import org.example.model.IssueRecord;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO {

    public void addIssueRecord(IssueRecord record) {
        String sql = "INSERT INTO issue_records (issue_id, book_id, student_id, issue_date, return_date, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, record.getIssueId());
            stmt.setInt(2, record.getBookId());
            stmt.setInt(3, record.getStudentId());
            stmt.setDate(4, Date.valueOf(record.getIssueDate()));
            if (record.getReturnDate() != null) {
                stmt.setDate(5, Date.valueOf(record.getReturnDate()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setString(6, record.getStatus());

            stmt.executeUpdate();
            System.out.println("Issue record added successfully with ID: " + record.getIssueId());
        } catch (SQLException e) {
            System.out.println("Error: Failed to add issue record - " + e.getMessage());
        }
    }

    public IssueRecord getIssueRecordById(int issueId) {
        String sql = "SELECT * FROM issue_records WHERE issue_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, issueId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToIssueRecord(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve issue record - " + e.getMessage());
        }

        return null;
    }

    public List<IssueRecord> getActiveIssuesByStudentId(int studentId) {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE student_id = ? AND status = 'ISSUED'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    records.add(mapResultSetToIssueRecord(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve active issues for student - " + e.getMessage());
        }

        return records;
    }

    public List<IssueRecord> getActiveIssuesByBookId(int bookId) {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE book_id = ? AND status = 'ISSUED'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    records.add(mapResultSetToIssueRecord(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve active issues for book - " + e.getMessage());
        }

        return records;
    }

    public List<IssueRecord> getAllIssueRecords() {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                records.add(mapResultSetToIssueRecord(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve issue records - " + e.getMessage());
        }

        return records;
    }

    public void updateIssueRecord(IssueRecord record) {
        String sql = "UPDATE issue_records SET book_id = ?, student_id = ?, issue_date = ?, return_date = ?, status = ? WHERE issue_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getStudentId());
            stmt.setDate(3, Date.valueOf(record.getIssueDate()));
            if (record.getReturnDate() != null) {
                stmt.setDate(4, Date.valueOf(record.getReturnDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setString(5, record.getStatus());
            stmt.setInt(6, record.getIssueId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Issue record updated successfully for ID: " + record.getIssueId());
            } else {
                System.out.println("Error: Issue record with ID " + record.getIssueId() + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to update issue record - " + e.getMessage());
        }
    }

    public boolean hasActiveIssues(int studentId) {
        String sql = "SELECT COUNT(*) FROM issue_records WHERE student_id = ? AND status = 'ISSUED'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to check active issues - " + e.getMessage());
        }

        return false;
    }

    private IssueRecord mapResultSetToIssueRecord(ResultSet rs) throws SQLException {
        IssueRecord record = new IssueRecord(
                rs.getInt("issue_id"),
                rs.getInt("book_id"),
                rs.getInt("student_id")
        );

        Date issueDate = rs.getDate("issue_date");
        if (issueDate != null) {
            record.setIssueDate(issueDate.toLocalDate());
        }

        Date returnDate = rs.getDate("return_date");
        if (returnDate != null) {
            record.setReturnDate(returnDate.toLocalDate());
        }

        record.setStatus(rs.getString("status"));

        return record;
    }
}
