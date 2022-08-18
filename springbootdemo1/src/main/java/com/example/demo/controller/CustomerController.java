package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 顾客管理类
 * @author taipanlang
 */
@RequestMapping("customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 账号管理：注册
     *
     * @param customer
     * @return 返回0表示用户已存在
     */
    @PostMapping("signup")
    public Integer signUp(@RequestBody Customer customer) {
        return customerService.signUp(customer);
    }

    /**
     * 账号管理：登录验证
     *
     * @param customer
     * @return
     */
    @PostMapping("login")
    public String tryLogin(@RequestBody Customer customer) {
        return customerService.tryLogin(customer);
    }

    /**
     * 账号管理:获取账号信息
     * 登录后获取用户信息.用于存储到前端缓存.
     *
     * @param customer
     * @return
     */
    @PostMapping("getinf")
    public Customer getInf(@RequestBody Customer customer) {
        return customerService.getInf(customer);
    }

    /**
     * 账号管理:更改昵称.
     *
     * @param customer
     */
    @PostMapping("change")
    public void changeName(@RequestBody Customer customer) {
        customerService.update(customer);
    }

    /**
     * 商品操作:添加商品到购物车
     * @param cart
     * @return
     */
    @PostMapping("addToCart")
    public String addToCart(@RequestBody Cart cart) {
        return customerService.addToCart(cart);
    }

    /**
     * 购物车显示:查找店铺
     * 查找订单涉及的店铺
     * @param cid
     * @return
     */
    @GetMapping("findshops")
    public List<Shops> findShops(Integer cid) {
        return customerService.findShops(cid);
    }

    /**
     * 购物车显示:查找店铺子项
     * 查找购物车中属于该店铺的商品.
     * @param cid
     * @param sid
     * @return
     */
    @GetMapping("findcarts")
    public List<ShowingCart> findCart(Integer cid, Integer sid) {
        return customerService.findCarts(cid, sid);
    }

    /**
     * 购物车操作:移除该商店所有物品
     * @param cid
     * @param sid
     */
    @GetMapping("removecarts")
    public void removeCarts(Integer cid, Integer sid) {
        customerService.removeCarts(cid, sid);
    }

    /**
     * 购物车操作:修改商品数量
     * todo:缺少一个商品存量判断,但是在支付时已有,可以暂时不做.用户在购物车加商品,就算超出在售数量也没有提示.可以在前端提示.
     * @param cart
     */
    @PostMapping("updatecart")
    public void updateCart(@RequestBody Cart cart) {
        customerService.updateCart(cart);
    }

    /**
     * 购物车操作:移除商品
     * @param gid
     */
    @GetMapping("removeacart")
    public void removeACart(Integer cid,Integer gid) {
        customerService.removeACart(cid,gid);
    }

    /**
     * 订单显示:获取某顾客订单
     * @param cid
     * @return
     */
    @GetMapping("getorders")
    private List<Orders> getOrders(Integer cid) {
        return customerService.getOrders(cid);
    }

    // TODO: 2022/8/18 应该属于GoodsController类,但是GoodsController没有拦截.不设为goodscontroller的,谁需要谁去service拿
    // TODO: 2022/8/18 完成商品修改时去掉图片
    /**
     * 订单显示:获取某订单的子项
     * @param oid
     * @return
     */
    @GetMapping("getorderson")
    private List<ShowingCart> getOrderson(Integer oid) {
        return goodsService.getOrderson(oid);
    }

    /**
     * 订单操作:用户确认完成订单
     * @param oid
     */
    @GetMapping("finishorders")
    private void finishOrders(Integer oid) {
        customerService.finishOrders(oid);
    }


    /**
     * 支付检测
     * 检测是否符合支付条件-检测在售数量是否充足.
     * 支付过程:支付检测->支付操作
     * @param showingCarts
     * @return 返回字符串列表, 存储库存不足物品对应的文字提示.使用:如果length=0,则库存充足,如果length!=0则输出字符串.
     */
    @PostMapping("detectamount")
    private List<String> detectAmount(@RequestBody List<ShowingCart> showingCarts) {
        return customerService.detectAmount(showingCarts);
    }

    /**
     * 支付操作
     * 和删除商家订单removecarts差不多，多了一步记录在order和orderson表上。
     * @param cid
     * @param sid
     * @param receiveWay
     */
    @GetMapping("pay")
    public String pay(Integer cid, Integer sid, @RequestParam("way") String receiveWay) {
        return customerService.pay(cid, sid, receiveWay);
    }
}
