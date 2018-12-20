package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.MenuEntity;

public interface MenuService extends IService<MenuEntity> {
    MenuEntity selectParent(Long parentid);
}
