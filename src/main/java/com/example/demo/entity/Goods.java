package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;



    private Long goodsid;
    private String goodsname;
    private Long goodstypeid;
    private double price;
    private String tel;
    private int istop;
    private String username;

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Long getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Long goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIstop() {
        return istop;
    }

    public void setIstop(int istop) {
        this.istop = istop;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @TableField(exist = false)
    private String value;
    @TableField(exist = false)
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }



    public void setLabel(String label) {
        this.label = label;
    }





    @Override
    public String toString() {
        return "Goods{" +
                "goodsid=" + goodsid +
                ", goodsname='" + goodsname + '\'' +
                ", goodstypeid=" + goodstypeid +
                ", price=" + price +
                ", istop=" + istop +
                '}';
    }
}
