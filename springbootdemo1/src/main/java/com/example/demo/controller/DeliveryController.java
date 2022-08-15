package com.example.demo.controller;

import com.example.demo.domain.Delivery;
import com.example.demo.mapper.DeliveryMapper;
import com.example.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

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
