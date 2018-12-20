package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleid;
    private String role;

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", role='" + role + '\'' +
                '}';
    }
}
