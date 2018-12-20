package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserSerivce;
import com.example.demo.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/comment/")
public class CommentController {
    @Resource
    CommentService commentService;
    @Resource
    UserSerivce userSerivce;

    @PostMapping("/selectByGoodsId")
    public List selectByGoodsId(@RequestBody CommentEntity entity){
        EntityWrapper<CommentEntity> EW =new EntityWrapper<>();
        EW.setEntity(entity);
        List<CommentEntity> list = commentService.selectList(EW);
        for(CommentEntity com : list){


//            关联用户
            com.setUser(userSerivce.SelectByID(com.getUserid()));
            com.getUser().setPassword(null);



            CommentEntity parent = commentService.selectByParentId(com.getParentid());
            com.setValue(com.getCommentid().toString());
            com.setLabel(com.getText());
            if (parent!=null){
                com.setParentname(parent.getText());
            }
//
        }
        return list;
    }

    @PostMapping("/save")
    public  R save(@RequestBody CommentEntity entity){


        if (entity.getParentid()==0){
            entity.setLevel(1);
        }else {
            entity.setLevel(2);
        }

        String loginName  =(String)  SecurityUtils.getSubject().getPrincipal();
        entity.setUserid(userSerivce.selectByUserName(loginName).getUserid());
        commentService.insert(entity);
        return R.ok();
    }




}
