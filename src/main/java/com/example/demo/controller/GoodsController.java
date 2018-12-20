package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.DTO.GoodsDto;
import com.example.demo.DTO.gsp;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsType;
import com.example.demo.entity.Pricture_info;
import com.example.demo.service.GoodsService;
import com.example.demo.service.GoodsTypeService;
import com.example.demo.service.Pricture_infoService;
import com.example.demo.service.UserSerivce;
import com.example.demo.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/goods/")
public class GoodsController {
    @Resource
    UserSerivce userSerivce;
    @Resource
    GoodsTypeService goodsTypeService;
    @Resource
    GoodsService goodsService;
    @Resource
    Pricture_infoService prictureInfoService;

    @PostMapping("/getAllTypes")
    public List<GoodsType> getAlltype()
    {
        List<GoodsType> list = goodsTypeService.selectList(null);
        for (GoodsType entity :list)
        {
            entity.setValue(entity.getGoodstypeid().toString());
            entity.setLabel(entity.getGoodstypename());
            GoodsType parent = goodsTypeService.selectParent(entity.getParentid());
            if (parent!=null)
            {
                entity.setParentname(parent.getGoodstypename());
            }
        }

        return list;
    }


    @PostMapping("/save")
    public R save(@RequestBody Goods goods)
    {
        goods.setUsername((String) SecurityUtils.getSubject().getPrincipal());
        if(goodsService.insert(goods)){


            EntityWrapper<Goods> EW = new EntityWrapper<>();
            EW.setEntity(goods);
            Long ID=  goodsService.selectOne(EW).getGoodsid();
            return  R.ok().put("msg","success").put("goodsid",ID);
        }
        else return R.error(1,"error");
    }


    @PostMapping("goodlist")
    public List<GoodsDto> goodsList(@RequestBody Goods goods){
        List<GoodsDto> GDlist =  new ArrayList<>();

        EntityWrapper<Goods> EW = new EntityWrapper<Goods>();
        EW.setEntity(goods);

        for(Goods entity: goodsService.selectList(EW)){
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




            GDlist.add(goodsDto);

        }
        return GDlist;
    }

    @RequestMapping("pricturesave")
    protected void savePricture(@RequestBody Pricture_info prictureInfo){
        prictureInfoService.insert(prictureInfo);
    }

    @RequestMapping("selectOneById")
    protected R  selectOneById(@RequestBody Goods goodss){
//        不能直接传Long。。。不知道为啥。。醉了。。
        Long goodsid = goodss.getGoodsid();


       Goods goods = goodsService.selectonebyid(goodsid);
       List<Pricture_info> imgurls = prictureInfoService.selectListByGoodsId(goodsid);
       GoodsDto rt = new GoodsDto();
       rt.setImgurls(imgurls);
       rt.setUsername(goods.getUsername());
       rt.setTel(goods.getTel());
       rt.setPrice(goods.getPrice());
       rt.setIstop(goods.getIstop());
       rt.setGoodsname(goods.getGoodsname());
       rt.setGoodsid(goods.getGoodsid());
       rt.setGoodstypeid(goods.getGoodstypeid());
       return R.ok().put("goodsdto",rt);
    }

    @RequestMapping("selectMyGoods")
    public List<GoodsDto> selectMyGoods(){

        String loginName  =(String)  SecurityUtils.getSubject().getPrincipal();

        Goods goods = new Goods();

        goods.setUsername(loginName);

        EntityWrapper<Goods> EW = new EntityWrapper<Goods>();

        EW.setEntity(goods);

        List<GoodsDto> GDlist =  new ArrayList<>();

        for(Goods entity : goodsService.selectList(EW)){
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




            GDlist.add(goodsDto);

        }
        return    GDlist;
    }

    @RequestMapping("/delectbyid")
    private R delectbyid(@RequestBody Goods goods){
        EntityWrapper<Goods> EW = new EntityWrapper();
        EW.setEntity(goods);
        return R.ok().put("msg",goodsService.delete(EW));
    }

    @RequestMapping("/delectpricture")
    private R delectPricture(@RequestBody Pricture_info prictureInfo){
        EntityWrapper<Pricture_info> EW = new EntityWrapper<>();
        EW.setEntity(prictureInfo);
        prictureInfoService.delete(EW);
        return R.ok();
    }

    @RequestMapping("/getTypesbylevel")
    private List getTypesbylevel(@RequestBody GoodsType goodsType){

        EntityWrapper<GoodsType> EW = new EntityWrapper<>();
        EW.setEntity(goodsType);
        return goodsTypeService.selectList(EW);
    }


    @RequestMapping("/addGoodsType")
    private R addGoodsType(@RequestBody GoodsType goodsType)
    {
        if (goodsTypeService.insert(goodsType)) {
            return R.ok();
        }
        else {
            return R.error(1,"添加失败");
        }
    }

    @RequestMapping("/delectTypeById")
    private R delectTypeById(@RequestBody GoodsType goodsType){


        EntityWrapper<GoodsType> EW = new EntityWrapper<>();
        EW.setEntity(goodsType);
        if(goodsTypeService.delete(EW)){
            return  R.ok();
        }
        else {
            return R.error(1,"error");
        }


    }

}
