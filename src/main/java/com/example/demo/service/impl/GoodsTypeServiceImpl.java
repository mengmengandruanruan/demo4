package com.example.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.GoodsTypeDao;
import com.example.demo.entity.GoodsType;
import com.example.demo.service.GoodsTypeService;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeDao,GoodsType> implements GoodsTypeService {
    @Override
    public GoodsType selectParent(long parentid) {
        GoodsType entity =new GoodsType();
        entity.setGoodstypeid(parentid);
        return baseMapper.selectOne(entity);
    }
}
