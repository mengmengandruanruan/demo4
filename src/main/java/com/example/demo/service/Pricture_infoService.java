package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Pricture_info;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Pricture_infoService extends IService<Pricture_info>{

    List<Pricture_info> selectListByGoodsId(Long id);
}
