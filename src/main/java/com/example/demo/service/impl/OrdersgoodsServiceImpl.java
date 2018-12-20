package com.example.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.OrdersgoodsDao;
import com.example.demo.entity.Ordersgoods;
import com.example.demo.service.OrdersgoodsService;
import org.springframework.stereotype.Service;

@Service
public class OrdersgoodsServiceImpl   extends ServiceImpl<OrdersgoodsDao,Ordersgoods> implements OrdersgoodsService {
}
