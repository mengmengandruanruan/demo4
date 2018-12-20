package com.example.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.OrdersDao;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Ordersgoods;
import com.example.demo.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersDao,Orders> implements OrdersService {
}
