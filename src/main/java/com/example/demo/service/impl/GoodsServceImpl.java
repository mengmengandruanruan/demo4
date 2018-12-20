package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.GoodsDao;
import com.example.demo.entity.Goods;
import com.example.demo.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServceImpl extends ServiceImpl<GoodsDao,Goods> implements GoodsService {
    @Override
    public Goods selectonebyid(Long id) {
        Goods entity = new Goods();
        entity.setGoodsid(id);
        EntityWrapper EW = new EntityWrapper();
        EW.setEntity(entity);
        return selectOne(EW);
    }
}
