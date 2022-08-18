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
     *
     * @param storeKeeper
     * @return
     */
    @PostMapping("signup")
    public Integer signUp(@RequestBody StoreKeeper storeKeeper) {
        return storeKeeperService.signUp(storeKeeper);
//        注册成功返回账号id，提醒用户记住。
    }

    /**
     * 账号管理：登录
     *
     * @param storeKeeper
     * @return
     */
    @PostMapping("login")
    public String tryLogin(@RequestBody StoreKeeper storeKeeper) {
        return storeKeeperService.tryLogin(storeKeeper);
    }

    @PostMapping("getinf")
    public StoreKeeper getInf(@RequestBody StoreKeeper storeKeeper) {
        return storeKeeperService.getInf(storeKeeper);
    }

    /**
     * 账号管理：修改（店主入口）
     * 注：账号封禁功能在管理员处。
     *
     * @param storeKeeper
     * @return
     */
    @PostMapping("update")
    public String update(@RequestBody StoreKeeper storeKeeper) {
//        在前端的提交前检测是否用户名和密码是没有变的。
        storeKeeperService.update(storeKeeper);
        return "更新成功";
    }

    /**
     * 搜索商家商品
     * 支持在线的和仓库的
     *
     * @param gname
     * @param state
     * @param sid
     * @return
     */
    @GetMapping("searchgoods")
    public List<ShowingGoods> searchGoodsByGname(String gname, String state, Integer sid) {
        return storeKeeperService.searchGoodsByGnamePlus(gname, state, sid);
    }

    ;

    /**
     * 获取商家的订单
     *
     * @param sid
     * @return
     */
    @GetMapping("getorders")
    public List<Orders> getOrders(Integer sid) {
        return storeKeeperService.getOrders(sid);
    }

    /**
     * 修改商家的订单状态
     *
     * @param oid
     * @param ostate
     */
    @GetMapping("updateodersstate")
    public void updateOrdersState(Integer oid, String ostate) {
        storeKeeperService.updateOdersState(oid, ostate);
    }

    /**
     * 商品操作-商家:添加商品
     *
     * @param goods
     * @return
     */
    @PostMapping("addgoods/base")
    public int addGoodsBase(@RequestBody Goods goods) {
        return storeKeeperService.addGoods(goods);
    }

    /**
     * 商品操作-商家:修改商品or上下架
     *
     * @param goods
     */
    @PostMapping("updategoods")
    public void updateGoods(@RequestBody Goods goods) {
        storeKeeperService.updateGoods(goods);
    }

    /**
     * 商品操作:
     *
     * @param showingGoods
     */
    @PostMapping("updategoodsstate")
    public void updateGoodsState(@RequestBody ShowingGoods showingGoods) {
        Goods goods = showingGoods.getGoods();
        goodsService.exchange(goods.getGid(), goods.getState());
    }

    /**
     * 商品操作-商家:设置定时上架
     * 和修改商品一起被前端调用.
     *
     * @param gid
     * @param time
     */
    @GetMapping("setgoodstime")
    public void setGoodsTime(Integer gid, Long time) {
        goodsService.setGoodsTime(gid, time);
    }

}
