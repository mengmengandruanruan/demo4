package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("goodstype")
public class GoodsType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long goodstypeid;
    private String goodstypename;
    private Long parentid;
    private Integer level;

    public Long getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Long goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    public String getGoodstypename() {
        return goodstypename;
    }

    public void setGoodstypename(String goodstypename) {
        this.goodstypename = goodstypename;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    //父级名称
    @TableField(exist = false)
    private String parentname;
    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    //子集
    @TableField(exist = false)
    private List<GoodsType> childen = new ArrayList<GoodsType>();

    public List<GoodsType> getChilden() {
        return childen;
    }

    public void setChilden(List<GoodsType> childen) {
        this.childen = childen;
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
        return "GoodsType{" +
                "goodstypeid=" + goodstypeid +
                ", goodstypename='" + goodstypename + '\'' +
                ", parentid=" + parentid +
                ", level=" + level +
                '}';
    }
}
