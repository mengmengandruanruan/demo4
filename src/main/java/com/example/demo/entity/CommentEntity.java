package com.example.demo.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("Comment")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long commentid;
    private Long parentid;
    private Integer level;
    private String text;
    private Long userid;
    private Long goodsid;

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
    private List<CommentEntity> childen = new ArrayList<CommentEntity>();

    public List<CommentEntity> getChilden() {
        return childen;
    }

    public void setChilden(List<CommentEntity> childen) {
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


    @TableField(exist = false)
    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentid=" + commentid +
                ", parentid=" + parentid +
                ", level=" + level +
                ", text='" + text + '\'' +
                ", userid=" + userid +
                ", goodsid=" + goodsid +
                ", parentname='" + parentname + '\'' +
                ", childen=" + childen +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
