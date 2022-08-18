package com.example.demo.service;

import com.example.demo.domain.Delivery;
import com.example.demo.domain.Orders;
import com.example.demo.domain.ShowingCart;
import com.example.demo.mapper.DeliveryMapper;
import com.example.demo.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;
    @Autowired
    private OrdersMapper ordersMapper;

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

    public List<Orders> getNewOrders() {
        return ordersMapper.findByState("已发货");
    }

    public List<Orders> getMyOrders(Integer did) {
        return ordersMapper.findByDid(did);
    }

    public void takeOrders(Integer oid,Integer did){
//        List<ShowingCart> byOid = ordersMapper.findByOid(oid);
        Orders ordersByOid = ordersMapper.findOrdersByOid(oid);
        ordersByOid.setDid(did);
        ordersByOid.setOstate("配送中");
        ordersMapper.updateDidByOid(ordersByOid);
    }
    public void finishOrders(Integer oid){
//        List<ShowingCart> byOid = ordersMapper.findByOid(oid);
        Orders ordersByOid = ordersMapper.findOrdersByOid(oid);
//        ordersByOid.setDid(did);
        ordersByOid.setOstate("已送达");
        ordersMapper.updateDidByOid(ordersByOid);
    }

}
