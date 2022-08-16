package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingCart;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 顾客操作，添加商品到cart表中。
     *
     * @param cart{cid,gid,oamount}
     */
    public String addToCart(Cart cart) {
        Cart record = cartMapper.findRecord(cart);
        if (record == null) {
            cartMapper.insert(cart);
        } else {
//            新增加的+购物车中的
            cart.setOamount(cart.getOamount() + record.getOamount());
            this.updateCart(cart);
//            cartMapper.updateRecord(cart);
        }
        return "已加入购物车";
    }

    public void updateCart(Cart cart){
        cartMapper.updateRecord(cart);
    }

    public void removeCarts(Integer cid, Integer sid) {
        List<Integer> byShop = goodsMapper.findByShop(sid);
        for (Integer i : byShop) {
            removeACart(i);
        }
    }

    public void removeACart(Integer gid) {
        cartMapper.delByGid(gid);
    }

    public Integer signUp(Customer customer) {
        customerMapper.insert(customer);
        return customer.getCid();
//        Customer byId = customerMapper.findById(customer.getCid());
//        if (byId==null){
//            customerMapper.insert(customer);
//            return customer.getCid();
//        }else {
//            return 0;
//        }
    }

    public String tryLogin(Customer customer) {
        Customer c = customerMapper.findById(customer.getCid());
        if (c == null) {
            return "用户不存在";
        }
        if (!c.getCpassword().equals(customer.getCpassword())) {
            return "密码错误";
        }
        return "登录验证成功";
    }

    public Customer getInf(Customer customer) {
        Customer c = customerMapper.findById(customer.getCid());
        if (c == null || (!c.getCpassword().equals(customer.getCpassword()))) {
            return null;
        }
        return c;
    }

    public void update(Customer customer) {
        customerMapper.update(customer);
    }

    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    public int add(Customer customer) {
        customerMapper.insert(customer);
        return customer.getCid();
    }

    public List<Shops> findShops(Integer cid) {
        return cartMapper.findShops(cid);
    }

    public List<ShowingCart> findCarts(Integer cid, Integer sid) {
        return cartMapper.findCarts(cid, sid);
    }
}
