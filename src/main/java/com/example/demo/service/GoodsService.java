package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Goods;

public interface GoodsService extends IService<Goods> {
    Goods selectonebyid(Long id);
}
