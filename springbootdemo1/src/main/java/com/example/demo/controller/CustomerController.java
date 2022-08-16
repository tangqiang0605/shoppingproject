package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.service.CustomerService;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodsService goodsService;

    @GetMapping("getorders")
    private List<Orders> getOrders(Integer cid){
        return customerService.getOrders(cid);
    }

    @GetMapping("getorderson")
    private List<ShowingCart> getOrderson(Integer oid){return customerService.getOrderson(oid);}

//    private

    /**
     * 账号管理：注册
     * @param customer
     * @return返回0表示用户已存在
     */
    @PostMapping("signup")
    public Integer signUp(@RequestBody Customer customer){
        return customerService.signUp(customer);
    }

    /**
     * 账号管理：登录
     * @param customer
     * @return
     */
    @PostMapping("login")
    public String tryLogin(@RequestBody Customer customer){
        return customerService.tryLogin(customer);
    }
    @PostMapping("getinf")
    public Customer getInf(@RequestBody Customer customer){
        return customerService.getInf(customer);
    }

    @PostMapping("change")
    public void changeName(@RequestBody Customer customer){
        customerService.update(customer);
    }

    @GetMapping("findall")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("addToCart")
    public String addToCart(@RequestBody Cart cart){
        return customerService.addToCart(cart);
    }

    @GetMapping("findshops")
    public List<Shops> findShops(Integer cid){return customerService.findShops(cid);}

    @GetMapping("findcarts")
    public List<ShowingCart> findCart(Integer cid,Integer sid){return customerService.findCarts(cid,sid);}

    @GetMapping("removeacart")
    public void removeACart(Integer gid){
        customerService.removeACart(gid);
    }

    @PostMapping("updatecart")
    public void updateCart(@RequestBody Cart cart){customerService.updateCart(cart);}

    @GetMapping("removecarts")
    public void removeCarts(Integer cid,Integer sid){
        customerService.removeCarts(cid,sid);
    }

    /**
     * 支付，和删除商家订单removecarts差不多，多了一步记录在order和orderson表上。
     * @param cid
     * @param sid
     * @param receiveWay
     */
    @GetMapping("pay")
    public void pay(Integer cid,Integer sid,@RequestParam("way") String receiveWay){
        customerService.pay(cid,sid,receiveWay);
    }
}
