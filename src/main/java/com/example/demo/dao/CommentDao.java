package com.example.demo.dao;


import com.example.demo.entity.CommentEntity;
import com.example.demo.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommentDao extends BaseDao<CommentEntity> {

}
