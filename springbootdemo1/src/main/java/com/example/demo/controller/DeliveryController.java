package com.example.demo.controller;

import com.example.demo.domain.Delivery;
import com.example.demo.domain.Orders;
import com.example.demo.domain.StoreKeeper;
import com.example.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配送员管理类
 * @author taipanlang
 */
@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    /**
     * 账户管理:注册
     *
     * @param delivery
     * @return
     */
    @PostMapping("signup")
    public Integer signUp(@RequestBody Delivery delivery) {
        return deliveryService.signUp(delivery);
    }

    /**
     * 账户管理:登录验证
     *
     * @param delivery
     * @return
     */
    @PostMapping("login")
    public String tryLogin(@RequestBody Delivery delivery) {
        return deliveryService.tryLogin(delivery);
    }

    /**
     * 账号管理:登录验证后获取用户信息
     *
     * @param delivery
     * @return
     */
    @PostMapping("getinf")
    public Delivery getInf(@RequestBody Delivery delivery) {
        return deliveryService.getInf(delivery);
    }

    @PostMapping("update")
    public String update(@RequestBody Delivery delivery) {
        deliveryService.update(delivery);
        return "更新成功";
    }

    /**
     * 订单显示:获取新订单
     *
     * @return
     */
    @GetMapping("neworders")
    public List<Orders> getNewOrders() {
        return deliveryService.getNewOrders();
    }

    /**
     * 订单显示:配送员-我的订单
     *
     * @param did
     * @return
     */
    @GetMapping("myorders")
    public List<Orders> getMyOrders(Integer did) {
        return deliveryService.getMyOrders(did);
    }

    /**
     * 订单操作:配送中
     * 配送员接单
     *
     * @param oid
     * @param did
     */
    @GetMapping("takeorders")
    public void takeOrders(Integer oid, Integer did) {
        deliveryService.takeOrders(oid, did);
    }

    /**
     * 订单操作:已送达
     * 配送员完成订单
     *
     * @param oid
     */
    @GetMapping("finishorders")
    public void takeOrders(Integer oid) {
        deliveryService.finishOrders(oid);
    }

}
