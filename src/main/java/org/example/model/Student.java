package org.example.model;

public class Student {

    public int studentId;
    private String name;
    private String Email;
    private String phone;

    public Student(int studentId,String name,String Email,String phone){
        this.studentId = studentId;
        this.name = name;
        this.Email = Email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student [ID=" + studentId +
                ", Name=" + name +
                ", Email=" + Email +
                ", Phone=" + phone + "]";
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
