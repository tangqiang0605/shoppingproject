package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.OrdersMapper;
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
    @Autowired
    private OrdersMapper ordersMapper;

    public List<Orders> getOrders(Integer cid){return ordersMapper.findByCid(cid);}

    public List<ShowingCart> getOrderson(Integer oid){return ordersMapper.findByOid(oid);}

    public void finishOrders(Integer oid){
        ordersMapper.updateOstateByOid(oid,"已完成");
    }

    public void pay(Integer cid, Integer sid, String receiveWay) {
//        确定购物车中是否有该店铺的商品
        List<ShowingCart> carts = cartMapper.findCarts(cid, sid);
        if (carts != null) {
//        记录
            Orders orders = new Orders(null, "待发货", null, cid, sid, receiveWay.equals("快跑配送"));
            ordersMapper.insert(orders);
            Integer oid = orders.getOid();
            for (ShowingCart s : carts) {
                ordersMapper.insertSon(oid, s.getGid(), s.getOamount());
            }
//        删除购物车
        this.removeCarts(cid, sid);
        }
    }

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

    public void updateCart(Cart cart) {
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
