package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.GoodsType;

public interface GoodsTypeService extends IService<GoodsType> {
    GoodsType selectParent(long parentid);

}
