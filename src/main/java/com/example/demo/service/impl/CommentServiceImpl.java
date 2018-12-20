package com.example.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.CommentDao;
import com.example.demo.entity.CommentEntity;
import com.example.demo.service.CommentService;
import com.example.demo.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao,CommentEntity> implements CommentService {
    @Override
    public CommentEntity selectByParentId(Long parentid) {
        CommentEntity entity = new CommentEntity();
        entity.setCommentid(parentid);
        return baseMapper.selectOne(entity);
    }
}
