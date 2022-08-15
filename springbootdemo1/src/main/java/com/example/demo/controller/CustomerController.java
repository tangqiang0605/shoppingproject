package com.example.demo.controller;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Customer;
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
        return goodsService.addToCart(cart);
    }
}
