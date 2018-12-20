package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("ordersgood")
public class Ordersgoods  implements Serializable {
        private Long  ordersgoodsid;
        private Long   goodsid;

    public Long getOrdersgoodsid() {
        return ordersgoodsid;
    }

    public void setOrdersgoodsid(Long ordersgoodsid) {
        this.ordersgoodsid = ordersgoodsid;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    @Override
    public String toString() {
        return "Ordersgoods{" +
                "ordersgoodsid=" + ordersgoodsid +
                ", goodsid=" + goodsid +
                '}';
    }
}
