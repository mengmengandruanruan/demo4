package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.DTO.GoodsDto;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Pricture_info;
import com.example.demo.service.*;
import com.example.demo.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/guanzhu/")
public class GuanzhuController {

    @Resource
    UserSerivce userSerivce;
    @Resource
    OrdersService ordersService;
    @Resource
    GoodsTypeService goodsTypeService;
    @Resource
    GoodsService goodsService;
    @Resource
    Pricture_infoService prictureInfoService;



    //    关注或取关
    @RequestMapping("guanzhu")
    private R guanzhu(@RequestBody Orders orders){
        String loginName  =(String)  SecurityUtils.getSubject().getPrincipal();
        orders.setUserid(userSerivce.selectByUserName(loginName).getUserid());
        EntityWrapper<Orders> EW = new EntityWrapper<>();
        EW.setEntity(orders);

        int x = ordersService.selectCount(EW);
        if (x==0){
            if(ordersService.insert(orders)){
                return R.ok().put("msg","关注成功");
            }
            else {
                return R.error(1,"异常");
            }
        }
        else {
            ordersService.delete(EW);
            return R.ok().put("msg","取消关注");
        }

    }

    @RequestMapping("gettype")
    private R guanzhutype(@RequestBody Orders orders){
        String loginName  =(String)  SecurityUtils.getSubject().getPrincipal();
        orders.setUserid(userSerivce.selectByUserName(loginName).getUserid());
        EntityWrapper<Orders> EW = new EntityWrapper<>();
        EW.setEntity(orders);
        int x = ordersService.selectCount(EW);
        if (x==0){
            return R.ok().put("type",true);
        }
        else {
            return R.ok().put("type",false);
        }
    }


    @RequestMapping("getgoodslist")
    private List<GoodsDto> getlist(){
        List<Goods> goods = new ArrayList<>();
        List<GoodsDto> list = new ArrayList<>();


        String loginName  =(String)  SecurityUtils.getSubject().getPrincipal();

        Orders orders = new Orders();

        orders.setUserid(userSerivce.selectByUserName(loginName).getUserid());

        EntityWrapper<Orders> EW = new EntityWrapper<>();

        EW.setEntity(orders);

        List<Orders> orderss = ordersService.selectList(EW);

        for (Orders entity:orderss){
           goods.add(goodsService.selectonebyid(entity.getGoodsid()));
        }

        for(Goods entity: goods){
            GoodsDto goodsDto = new GoodsDto();

//           获取商品对应的图片集合
            Pricture_info p  = new Pricture_info();
            p.setGoodsid(entity.getGoodsid());
            EntityWrapper E = new EntityWrapper();
            E.setEntity(p);


            goodsDto.setImgurls( prictureInfoService.selectList(E));


            goodsDto.setGoodsid(entity.getGoodsid());
            goodsDto.setGoodsname(entity.getGoodsname());
            goodsDto.setGoodstypeid(entity.getGoodstypeid());
            goodsDto.setIstop(entity.getIstop());
            goodsDto.setPrice(entity.getPrice());
            goodsDto.setTel(entity.getTel());
            goodsDto.setUsername(entity.getUsername());

                orders.setGoodsid(goodsDto.getGoodsid());
                EntityWrapper<Orders> ew = new EntityWrapper();
                Orders o = ordersService.selectOne(ew);

            goodsDto.setValue(""+o.getOrdersid());

           list.add(goodsDto);

        }

        return list;
    }



}
