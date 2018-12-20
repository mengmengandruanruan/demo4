package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/menu/")
public class MenuController {
    @Resource
    private MenuService menuService;

    private final Logger logger = LoggerFactory.getLogger( MenuController.class);

    @PostMapping("selectlist")
    public List<MenuEntity> selectAllMenu(){
        List<MenuEntity> list = menuService.selectList(null);

        for (MenuEntity entity :list)
        {
            entity.setValue(entity.getMenuid().toString());
            entity.setLabel(entity.getName());
            // 查找父级对象是否存在
            MenuEntity parent =  menuService.selectParent(entity.getParentid());
            if(parent!=null)
            {
             entity.setParentname(parent.getName());
            }
        }

        return list;
    }

}
