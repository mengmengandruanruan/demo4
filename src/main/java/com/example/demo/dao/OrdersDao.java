package com.example.demo.dao;

import com.example.demo.entity.Orders;
import com.example.demo.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersDao extends BaseDao<Orders> {
}
