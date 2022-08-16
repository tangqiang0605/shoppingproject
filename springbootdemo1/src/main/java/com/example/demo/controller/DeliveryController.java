package com.example.demo.controller;

import com.example.demo.domain.Delivery;
import com.example.demo.domain.Orders;
import com.example.demo.mapper.DeliveryMapper;
import com.example.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("takeorders")
    public void takeOrders(Integer oid,Integer did){
        deliveryService.takeOrders(oid,did);
    }

    @GetMapping("finishorders")
    public void takeOrders(Integer oid){
        deliveryService.finishOrders(oid);
    }


    @GetMapping("neworders")
    public List<Orders> getNewOrders(){
        return deliveryService.getNewOrders();
    }

    @GetMapping("myorders")
    public List<Orders> getMyOrders(Integer did){
        return deliveryService.getMyOrders(did);
    }

    @PostMapping("signup")
    public Integer signUp(@RequestBody Delivery delivery){
        return deliveryService.signUp(delivery);
//        注册成功返回账号id，提醒用户记住。
    }

    @PostMapping("login")
    public String tryLogin(@RequestBody Delivery delivery){
        return deliveryService.tryLogin(delivery);
    }
    @PostMapping("getinf")
    public Delivery getInf(@RequestBody Delivery delivery){
        return deliveryService.getInf(delivery);
    }
}
