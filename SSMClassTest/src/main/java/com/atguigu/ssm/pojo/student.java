package com.atguigu.ssm.pojo;

public class student {
    private String studentId;
    private String studentName;

   private String borrowedBooks;

    public student() {
    }

    @Override
    public String toString() {
        return "student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", borrowedBooks='" + borrowedBooks + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(String borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public student(String studentId, String studentName, String borrowedBooks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.borrowedBooks = borrowedBooks;
    }
}
