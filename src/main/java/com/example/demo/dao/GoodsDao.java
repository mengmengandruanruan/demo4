package com.example.demo.dao;

import com.example.demo.entity.Goods;
import com.example.demo.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsDao extends BaseDao<Goods> {
}
