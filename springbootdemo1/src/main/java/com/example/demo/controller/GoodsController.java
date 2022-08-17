package com.example.demo.controller;

import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingGoods;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("show")
    public List<ShowingGoods> show(){
        return goodsService.show();
    }

    @GetMapping("findall")
    public List<Goods> findAll(){
        return goodsService.findAll();
    }

    @GetMapping("shops")
    public List<Shops> allShops(){
        return goodsService.allShops();
    }

    @GetMapping("shop")
    public List<ShowingGoods> showShop(Integer sid){
//        System.out.println(sid);
        return goodsService.showShop(sid);
    }

    @GetMapping("searchgoods")
    public List<ShowingGoods> searchGoods(String gname){
        return goodsService.searchGoods(gname);
    }
    @GetMapping("searchshops")
    public List<Shops> searchShops(String sname){
        return goodsService.searchShop(sname);
    }


    @GetMapping("setgoodstime")
    public void setGoodsTime(Integer gid,Long time){
        // finish: 2022/8/17 long为空怎么办
        goodsService.setGoodsTime(gid,time);
    }

}
