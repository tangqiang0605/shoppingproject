package com.example.demo.service;

import com.example.demo.domain.Delivery;
import com.example.demo.mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

    public Integer signUp(Delivery delivery){
        deliveryMapper.insert(delivery);
        return delivery.getDid();
    }
    public String tryLogin(Delivery delivery){
        Delivery s = deliveryMapper.findById(delivery.getDid());
        if (s==null){
            return "用户不存在";
        }
        if(!s.getDpassword().equals(delivery.getDpassword())){
            return "密码错误";
        }

        return "登录验证成功";
    }
    public Delivery getInf(Delivery delivery){
        Delivery s=deliveryMapper.findById(delivery.getDid());
        if (s==null||(!s.getDpassword().equals(delivery.getDpassword()))){
            return null;
        }
        return s;
    }

    public int update(Delivery delivery){
        deliveryMapper.update(delivery);
        return delivery.getDid();
    }

}
