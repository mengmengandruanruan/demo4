package com.example.demo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.ConstantQiniu;
import com.example.demo.entity.GoodsType;
import com.example.demo.entity.MenuEntity;
import com.example.demo.service.*;
import com.example.demo.service.impl.UserServiceImpl;
import com.qiniu.util.Auth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    private UserSerivce userSerivce;
    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsTypeService goodsTypeService;
    @Autowired
    private ConstantQiniu constantQiniu;
    @Autowired
    private MenuService menuService;
    @Autowired
    private CommentService cService;


    @Test
    public void contextLoads() {
        CommentEntity entity = new CommentEntity();
        entity.setGoodsid(34L);
        EntityWrapper<CommentEntity> EW =new EntityWrapper<>();
        EW.setEntity(entity);
        List<CommentEntity> list = cService.selectList(EW);
        for(CommentEntity com : list){

            CommentEntity parent = cService.selectByParentId(com.getParentid());

            if (parent!=null){

                com.setParentname(parent.getText());
            }
        }
        System.out.println(list);
    }

}
