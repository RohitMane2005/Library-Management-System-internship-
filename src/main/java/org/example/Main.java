package org.example;

import org.example.model.Student;
import org.example.service.StudentService;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        // =================================
        // 1. ADD STUDENTS
        // =================================
        System.out.println("\n========== ADD STUDENTS ==========");

        Student s1 = new Student(
                101, "Rohit Mane", "rohit@gmail.com", "9876543210"
        );

        Student s2 = new Student(
                102, "Amit Patil", "amit@gmail.com", "9876543211"
        );

        Student s3 = new Student(
                103, "Sneha Joshi", "sneha@gmail.com", "9876543212"
        );

        studentService.addStudent(s1);
        studentService.addStudent(s2);
        studentService.addStudent(s3);


        // =================================
        // 2. DUPLICATE STUDENT TEST
        // =================================
        System.out.println("\n========== DUPLICATE STUDENT TEST ==========");

        Student duplicate = new Student(
                101, "Another Student", "another@gmail.com", "9876543213"
        );

        studentService.addStudent(duplicate);


        // =================================
        // 3. SEARCH EXISTING STUDENT
        // =================================
        System.out.println("\n========== SEARCH EXISTING STUDENT ==========");

        Student foundStudent = studentService.searchStudentById(102);

        if (foundStudent != null) {
            System.out.println("Student Found!");
            System.out.println(foundStudent);
        } else {
            System.out.println("Student not found!");
        }


        // =================================
        // 4. SEARCH NON-EXISTING STUDENT
        // =================================
        System.out.println("\n========== SEARCH INVALID STUDENT ==========");

        Student notFound = studentService.searchStudentById(999);

        if (notFound == null) {
            System.out.println("Student not found!");
        }


        // =================================
        // 5. VIEW ALL STUDENTS
        // =================================
        System.out.println("\n========== ALL STUDENTS ==========");

        studentService.viewAllStudents();


        // =================================
        // 6. UPDATE STUDENT
        // =================================
        System.out.println("\n========== UPDATE STUDENT ==========");

        studentService.updateStudent(
                102,
                "Amit Kumar",
                "amitkumar@gmail.com",
                "9876543214"
        );


        // =================================
        // 7. VERIFY UPDATED STUDENT
        // =================================
        System.out.println("\n========== VERIFY UPDATE ==========");

        Student updatedStudent = studentService.searchStudentById(102);

        if (updatedStudent != null) {
            System.out.println(updatedStudent);
        }


        // =================================
        // 8. UPDATE NON-EXISTING STUDENT
        // =================================
        System.out.println("\n========== UPDATE INVALID STUDENT ==========");

        studentService.updateStudent(
                999,
                "Test Student",
                "test@gmail.com",
                "9876543215"
        );


        // =================================
        // 9. DELETE STUDENT
        // =================================
        System.out.println("\n========== DELETE STUDENT ==========");

        studentService.deleteStudent(103);


        // =================================
        // 10. VERIFY DELETION
        // =================================
        System.out.println("\n========== VERIFY DELETION ==========");

        Student deletedStudent = studentService.searchStudentById(103);

        if (deletedStudent == null) {
            System.out.println("Student successfully deleted.");
        }


        // =================================
        // 11. DELETE SAME STUDENT AGAIN
        // =================================
        System.out.println("\n========== DELETE INVALID STUDENT ==========");

        studentService.deleteStudent(103);


        // =================================
        // 12. FINAL STUDENT LIST
        // =================================
        System.out.println("\n========== FINAL STUDENT LIST ==========");

        studentService.viewAllStudents();
    }
}