package com.example.demo.dao;
import com.example.demo.entity.MenuEntity;
import com.example.demo.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuDao extends BaseDao<MenuEntity> {
}
