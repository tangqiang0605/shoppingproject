package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.GoodsService;
import com.example.demo.service.StoreKeeperService;
import com.sun.javaws.IconUtil;
import org.apache.catalina.Store;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("storekeeper")
public class StoreKeeperController {
    @Autowired
    private StoreKeeperService storeKeeperService;

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 账号管理：注册
     * @param storeKeeper
     * @return
     */
    @PostMapping("signup")
    public Integer signUp(@RequestBody StoreKeeper storeKeeper){
        return storeKeeperService.signUp(storeKeeper);
//        注册成功返回账号id，提醒用户记住。
    }

    /**
     * 账号管理：登录
     * @param storeKeeper
     * @return
     */
    @PostMapping("login")
    public String tryLogin(@RequestBody StoreKeeper storeKeeper){
        return storeKeeperService.tryLogin(storeKeeper);
    }
    @PostMapping("getinf")
    public StoreKeeper getInf(@RequestBody StoreKeeper storeKeeper){
        return storeKeeperService.getInf(storeKeeper);
    }

    /**
     * 账号管理：修改（店主入口）
     * 注：账号封禁功能在管理员处。
     * @param storeKeeper
     * @return
     */
    @PostMapping("update")
    public String update(@RequestBody StoreKeeper storeKeeper){
//        在前端的提交前检测是否用户名和密码是没有变的。
        storeKeeperService.update(storeKeeper);
        return "更新成功";
    }

    @PostMapping("addgoods/base")
    public int addGoodsBase(@RequestBody Goods goods){
        return storeKeeperService.addGoods(goods);
    }

    @GetMapping("findgoodsbysid")
    public List<ShowingGoods> findGoodsBySid(Integer sid){
        return storeKeeperService.findGoodsBySid(sid);
    }

    @GetMapping("showrepository")
    public List<ShowingGoods> showRepository(Integer sid){return storeKeeperService.findRepository(sid);}

    @PostMapping("updategoodsstate")
    public void updateGoodsState(@RequestBody ShowingGoods showingGoods){
        Goods goods = showingGoods.getGoods();
//        System.out.println(goods);

        goodsService.exchange(goods.getGid(),goods.getState());
    }

    @GetMapping("searchgoods")
    public List<ShowingGoods> searchGoodsByGname(String gname,String state,Integer sid){
        return storeKeeperService.searchGoodsByGnamePlus(gname, state,sid);
    };

    @GetMapping("updateodersstate")
    public void updateOrdersState(Integer oid,String ostate)
    {
        storeKeeperService.updateOdersState(oid,ostate);
    }

    @GetMapping("getorders")
    public List<Orders> getOrders(Integer sid) {
        return storeKeeperService.getOrders(sid);
    }

//    @GetMapping("time")
//    public void test4time(Date time){
//
//        System.out.println(time);
//        Date time2=new Date();
//        System.out.println(time2);
//        goodsMapper.updateTime(time);
//        System.out.println(time);
//    }

    @PostMapping("updategoods")
    public void updateGoods(@RequestBody Goods goods){
        storeKeeperService.updateGoods(goods);
//        System.out.println(goods);
    }

}
