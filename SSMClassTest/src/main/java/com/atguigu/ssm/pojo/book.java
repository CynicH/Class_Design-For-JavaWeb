package com.atguigu.ssm.pojo;

import org.apache.ibatis.annotations.Param;

public class book {
    private Integer  bookId;

    private String bookName;
    private String picture;
    private Integer status ;

    @Override
    public String toString() {
        return "book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", picture='" + picture + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public book(Integer bookId, String bookName, String picture, Integer status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.picture = picture;
        this.status = status;
    }

    public book() {
    }
}
