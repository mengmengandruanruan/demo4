package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("orders")
public class Orders implements Serializable {
    private Long ordersid;
    private Long  userid;
    private Long goodsid;

    public Long getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(Long ordersid) {
        this.ordersid = ordersid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersid=" + ordersid +
                ", userid=" + userid +
                '}';
    }
}
