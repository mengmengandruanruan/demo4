package com.example.demo.DTO;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Pricture_info;

import java.util.List;

public class GoodsDto  extends Goods {
       private List<Pricture_info> imgurls;

    public List<Pricture_info> getImgurls() {
        return imgurls;
    }

    public void setImgurls(List<Pricture_info> imgurls) {
        this.imgurls = imgurls;
    }
}
