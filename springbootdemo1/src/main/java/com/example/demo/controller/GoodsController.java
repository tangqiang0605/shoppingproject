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

/**
 * 网站公开页相关接口
 * @author taipanlang
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 搜索商品
     * @param gname
     * @return
     */
    @GetMapping("searchgoods")
    public List<ShowingGoods> searchGoods(String gname) {
        return goodsService.searchGoods(gname);
    }

    /**
     * 搜索店铺
     * @param sname
     * @return
     */
    @GetMapping("searchshops")
    public List<Shops> searchShops(String sname) {
        return goodsService.searchShop(sname);
    }

    /**
     * 商店页面
     * @param sid
     * @return
     */
    @GetMapping("shop")
    public List<ShowingGoods> showShop(Integer sid) {
        return goodsService.showShop(sid);
    }
}
