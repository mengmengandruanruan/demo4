package com.example.demo.DTO;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Kcrbb {
    private String spmc;//商品名称
    private String sx101;//规格
    private String sx102;//克重
    private String sx106;//等级
    private String scrq;//生产日期
    private String rkslt;//入库数量吨
    private String rkslj;//入库数量件
    private String ckslt;//出库数量吨
    private String ckslj;//出库数量件
    private Double zkcl;//总库存量
    private Date beginDate;//开始时间
    private Date endDate;//结束时间
    private String js;//件数
    private String sx107;//白度
    private String ckmc;//仓库名称
    @ExcelField(title="商品名称",sort = 100)
    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }
    @ExcelField(title="规格",sort = 200)
    public String getSx101() {
        return sx101;
    }

    public void setSx101(String sx101) {
        this.sx101 = sx101;
    }
    @ExcelField(title="克重",sort = 300)
    public String getSx102() {
        return sx102;
    }

    public void setSx102(String sx102) {
        this.sx102 = sx102;
    }
    @ExcelField(title="品牌",sort = 400)
    public String getSx106() {
        return sx106;
    }

    public void setSx106(String sx106) {
        this.sx106 = sx106;
    }
    @ExcelField(title="生产日期",sort = 500)
    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }
    @ExcelField(title="入库数量（吨）",sort = 600)
    public String getRkslt() {
        return rkslt;
    }

    public void setRkslt(String rkslt) {
        this.rkslt = rkslt;
    }
    @ExcelField(title="入库数量（件）",sort = 700)
    public String getRkslj() {
        return rkslj;
    }

    public void setRkslj(String rkslj) {
        this.rkslj = rkslj;
    }
    @ExcelField(title="出库数量（吨）",sort = 800)
    public String getCkslt() {
        return ckslt;
    }

    public void setCkslt(String ckslt) {
        this.ckslt = ckslt;
    }
    @ExcelField(title="出库数量（件）",sort = 900)
    public String getCkslj() {
        return ckslj;
    }

    public void setCkslj(String ckslj) {
        this.ckslj = ckslj;
    }
    @ExcelField(title="总库存量",sort = 1000)
    public Double getZkcl() {
        return zkcl;
    }

    public void setZkcl(Double zkcl) {
        this.zkcl = zkcl;
    }
    @ExcelField(title="件数",sort = 1100)
    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }
    @ExcelField(title="等级",sort = 1200)
    public String getSx107() {
        return sx107;
    }

    public void setSx107(String sx107) {
        this.sx107 = sx107;
    }
    @ExcelField(title="仓库名称",sort = 1300)
    public String getCkmc() {
        return ckmc;
    }

    public void setCkmc(String ckmc) {
        this.ckmc = ckmc;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Kcrbb{" +
                "spmc='" + spmc + '\'' +
                ", sx101='" + sx101 + '\'' +
                ", sx102='" + sx102 + '\'' +
                ", sx106='" + sx106 + '\'' +
                ", scrq='" + scrq + '\'' +
                ", rkslt='" + rkslt + '\'' +
                ", rkslj='" + rkslj + '\'' +
                ", ckslt='" + ckslt + '\'' +
                ", ckslj='" + ckslj + '\'' +
                ", zkcl=" + zkcl +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", js='" + js + '\'' +
                ", sx107='" + sx107 + '\'' +
                ", ckmc='" + ckmc + '\'' +
                '}';
    }
}
