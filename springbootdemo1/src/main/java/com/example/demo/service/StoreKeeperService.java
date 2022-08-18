package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.OrdersMapper;
import com.example.demo.mapper.StoreKeeperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreKeeperService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StoreKeeperMapper storeKeeperMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    public Integer signUp(StoreKeeper storeKeeper){
        storeKeeperMapper.insert(storeKeeper);
        return storeKeeper.getSid();
    }
    public String tryLogin(StoreKeeper storeKeeper){
        StoreKeeper s = storeKeeperMapper.findById(storeKeeper.getSid());
        if (s==null){
            return "用户不存在";
        }
        if(!s.getSpassword().equals(storeKeeper.getSpassword())){
            return "密码错误";
        }
        if (s.getIsban()){
            return "账号封禁中";
        }
        return "登录验证成功";
    }
    public StoreKeeper getInf(StoreKeeper storeKeeper){
        StoreKeeper s=storeKeeperMapper.findById(storeKeeper.getSid());
        if (s==null||(!s.getSpassword().equals(storeKeeper.getSpassword()))){
            return null;
        }
        return s;
    }

    public int update(StoreKeeper storeKeeper){
        storeKeeperMapper.update(storeKeeper);
        return storeKeeper.getSid();
    }

    public List<ShowingGoods> findGoods(String gname, String state, Integer sid) {
        return goodsMapper.selectByNamePlus(gname, state,sid);
    }

    public int addGoods(Goods goods){
        goodsMapper.insert(goods);
        return goods.getGid();
    }

    public void updateGoods(Goods goods){
        goodsMapper.update(goods);
    }

    public List<Orders> getOrders(Integer sid) {
        return ordersMapper.findBySid(sid);
    }

    public void updateOdersState(Integer oid,String ostate){
        ordersMapper.updateOstateByOid(oid,ostate);
    }

}
