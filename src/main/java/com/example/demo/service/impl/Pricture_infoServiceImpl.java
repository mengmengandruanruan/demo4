package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.Pricture_infoDao;
import com.example.demo.entity.Pricture_info;
import com.example.demo.service.Pricture_infoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pricture_infoServiceImpl  extends ServiceImpl<Pricture_infoDao,Pricture_info> implements Pricture_infoService {
    @Override
    public List<Pricture_info> selectListByGoodsId(Long id) {
        EntityWrapper EW = new EntityWrapper();
        Pricture_info entity = new Pricture_info();
        entity.setGoodsid(id);
        EW.setEntity(entity);
        return selectList(EW);
    }
}
