package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.CommentEntity;

public interface CommentService  extends IService<CommentEntity> {
    CommentEntity selectByParentId(Long parentid);
}
