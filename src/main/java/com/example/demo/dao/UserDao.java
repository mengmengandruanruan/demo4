package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<User> {

}
