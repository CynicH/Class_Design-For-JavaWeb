package com.atguigu.ssm.pojo;

public class borrow {
    private Integer id;
    private student student;
    private book book;
    private String borrow_time;
    private String deadline;

    public borrow() {
    }

    @Override
    public String toString() {
        return "borrow{" +
                "id=" + id +
                ", student=" + student +
                ", book=" + book +
                ", borrow_time='" + borrow_time + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.atguigu.ssm.pojo.student getStudent() {
        return student;
    }

    public void setStudent(com.atguigu.ssm.pojo.student student) {
        this.student = student;
    }

    public com.atguigu.ssm.pojo.book getBook() {
        return book;
    }

    public void setBook(com.atguigu.ssm.pojo.book book) {
        this.book = book;
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public borrow(Integer id, com.atguigu.ssm.pojo.student student, com.atguigu.ssm.pojo.book book, String borrow_time, String deadline) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.borrow_time = borrow_time;
        this.deadline = deadline;
    }
}
