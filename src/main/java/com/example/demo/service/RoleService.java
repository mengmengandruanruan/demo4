package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;

import com.example.demo.entity.Role;

public interface RoleService extends IService<Role> {

    Role selectbyid(Long roleid);
}
