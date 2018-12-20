package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.User;

public interface UserSerivce extends IService<User> {
    User selectByUserName(String username);

    User SelectByID (Long ID);
}
