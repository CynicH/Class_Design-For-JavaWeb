package com.atguigu.ssm.pojo;

public class borrowResult {
   private String studentId;
   private String studentName;
   private String borrowedBooks;
   private String borrowTime;
   private String  deadline;
   private String picture;

   @Override
   public String toString() {
      return "borrowResult{" +
              "studentId='" + studentId + '\'' +
              ", studentName='" + studentName + '\'' +
              ", borrowedBooks='" + borrowedBooks + '\'' +
              ", borrowTime='" + borrowTime + '\'' +
              ", deadline='" + deadline + '\'' +
              ", picture='" + picture + '\'' +
              '}';
   }

   public String getPicture() {
      return picture;
   }

   public void setPicture(String picture) {
      this.picture = picture;
   }

   public borrowResult(String studentId, String studentName, String borrowedBooks, String borrowTime, String deadline, String picture) {
      this.studentId = studentId;
      this.studentName = studentName;
      this.borrowedBooks = borrowedBooks;
      this.borrowTime = borrowTime;
      this.deadline = deadline;
      this.picture = picture;
   }

   public borrowResult() {
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

   public String getBorrowTime() {
      return borrowTime;
   }

   public void setBorrowTime(String borrowTime) {
      this.borrowTime = borrowTime;
   }

   public String getDeadline() {
      return deadline;
   }

   public void setDeadline(String deadline) {
      this.deadline = deadline;
   }

   public borrowResult(String studentId, String studentName, String borrowedBooks, String borrowTime, String deadline) {
      this.studentId = studentId;
      this.studentName = studentName;
      this.borrowedBooks = borrowedBooks;
      this.borrowTime = borrowTime;
      this.deadline = deadline;
   }
}
