package org.example.model;

public class Student {

    public int studentId;
    private String name;
    private String mail;
    private String phone;

    public Student(int studentId,String name,String mail,String phone){
        this.studentId = studentId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
