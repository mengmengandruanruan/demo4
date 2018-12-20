package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserSerivce;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserSerivce {

    @Override
    public User selectByUserName(String username) {
        User user = new User();
        EntityWrapper EW = new EntityWrapper();
        user.setUsername(username);
        EW.setEntity(user);
        return selectOne(EW);
    }

    @Override
    public User SelectByID(Long ID) {
        User user = new User();
        EntityWrapper EW = new EntityWrapper();
        user.setUserid(ID);
        EW.setEntity(user);
        return selectOne(EW);
    }
}
