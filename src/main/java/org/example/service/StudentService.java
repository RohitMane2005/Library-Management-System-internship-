package org.example.service;

import org.example.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public Student searchStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Student details cannot be null.");
            return;
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            System.out.println("Error: Student name cannot be empty.");
            return;
        }

        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            System.out.println("Error: Invalid email address provided.");
            return;
        }

        if (student.getPhone() == null || student.getPhone().trim().isEmpty()) {
            System.out.println("Error: Phone number cannot be empty.");
            return;
        }

        String cleanPhone = student.getPhone().replaceAll("\\D", "");
        student.setPhone(cleanPhone);

        if (cleanPhone.length() < 10) {
            System.out.println("Error: Phone number cannot be less than ten digits.");
            return;
        } else if (cleanPhone.length() > 10) {
            System.out.println("Error: Phone number cannot be greater than ten digits.");
            return;
        }

        if (searchStudentById(student.getStudentId()) != null) {
            System.out.println("Error: Student with ID " + student.getStudentId() + " already exists.");
            return;
        }

        students.add(student);
        System.out.println("Success: Student added successfully with ID: " + student.getStudentId());
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered in the system.");
            return;
        }

        System.out.println("--- Registered Students List ---");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void updateStudent(int studentId, String name, String email, String phone) {
        Student student = searchStudentById(studentId);

        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Updated name cannot be empty.");
            return;
        }

        if (email == null || !email.contains("@")) {
            System.out.println("Error: Updated email address is invalid.");
            return;
        }

        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Error: Phone number cannot be empty.");
            return;
        }

        String cleanPhone = student.getPhone().replaceAll("\\D", "");
        if (cleanPhone.length() < 10) {
            System.out.println("Error: Phone number cannot be less than ten digits.");
            return;
        } else if (cleanPhone.length() > 10) {
            System.out.println("Error: Phone number cannot be greater than ten digits.");
            return;
        }

        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);

        System.out.println("Success: Student details updated successfully for ID: " + studentId);
    }

    public void deleteStudent(int studentId) {
        // 1. Search for the student
        Student student = searchStudentById(studentId);

        // 2. Check whether the student exists
        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " does not exist.");
            return;
        }

        // 3. Important Business Rule Check:
        // Once Issue and Return functionality is implemented, uncomment and verify:
        // if (issueService.hasActiveIssues(studentId)) {
        //     System.out.println("Error: Cannot delete student. Student has active book issues.");
        //     return;
        // }

        // 4. Remove the student from ArrayList
        students.remove(student);

        // 5. Display a success message
        System.out.println("Success: Student with ID " + studentId + " has been removed successfully.");
    }

}