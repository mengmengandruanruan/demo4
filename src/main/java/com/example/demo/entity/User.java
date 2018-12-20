package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
@TableName("User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userid;

    private String username;

    private String password;

    private Long roleid;

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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
