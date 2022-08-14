package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingGoods;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CartMapper cartMapper;

    public List<Goods> findAll(){
        return goodsMapper.findAll();
    }

    /**
     * 获取已上架的商品。
     * @return 已上架的商品showinggoods列表。
     */
    public List<ShowingGoods> show(){
        return goodsMapper.show();
    }

    /**
     * 获取所有店铺信息用于店铺列表展示。
     * @return
     */
    public List<Shops> allShops(){return goodsMapper.findAllShops();}

    /**
     * 店主操作，添加商品到goods表中。
     * @param goods
     * @return
     */
    public int add(Goods goods){
        goodsMapper.insert(goods);
        return goods.getGid();
    }

    /**
     * 顾客操作，添加商品到cart表中。
     * @param cart{cid,gid,oamount}
     */
    public String addToCart(Cart cart){
        Cart record=cartMapper.findRecord(cart);
        if (record==null){
            cartMapper.insert(cart);
        }else
        {
            cart.setOamount(cart.getOamount()+record.getOamount());
            cartMapper.updateRecord(cart);
        }
        return "已加入购物车";
    }

}
