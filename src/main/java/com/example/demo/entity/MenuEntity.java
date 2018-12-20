package com.example.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("Menu")
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

        @TableId
          private Long menuid;
          private Long  parentid;
          private String  name;
          private String  url;
          private Integer  level;
          private String  icon;

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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


}
