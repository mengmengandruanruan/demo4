package com.example.demo.dao;

import com.example.demo.entity.Role;
import com.example.demo.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseDao<Role> {
}
