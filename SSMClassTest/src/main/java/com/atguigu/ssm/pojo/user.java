package com.atguigu.ssm.pojo;

public class user {
    private String username;
    private String password;

    private  String root;

    public user(String username, String password, String root) {
        this.username = username;
        this.password = password;
        this.root = root;
    }

    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", root='" + root + '\'' +
                '}';
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public user() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public user(String username, String password) {

        this.username = username;
        this.password = password;
    }
}
