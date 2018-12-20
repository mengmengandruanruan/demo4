package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.MenuDao;
import com.example.demo.entity.GoodsType;

import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao,MenuEntity>  implements MenuService {
    @Override
    public MenuEntity selectParent(Long parentid) {
        MenuEntity entity =new MenuEntity();
        entity.setMenuid(parentid);
        return baseMapper.selectOne(entity);
    }
}
