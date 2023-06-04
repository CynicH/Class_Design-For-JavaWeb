package com.atguigu.ssm.pojo;

public class book1 {

    private String bookName;


    private String picture;
    private Integer status ;

    @Override
    public String toString() {
        return "book1{" +
                "bookName='" + bookName + '\'' +
                ", picture='" + picture + '\'' +
                ", status=" + status +
                '}';
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public book1(String bookName, String picture, Integer status) {
        this.bookName = bookName;
        this.picture = picture;
        this.status = status;
    }

    public book1() {
    }
}
