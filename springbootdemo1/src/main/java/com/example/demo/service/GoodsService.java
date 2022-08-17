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

    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    /**
     * 获取已上架的商品。
     *
     * @return 已上架的商品showinggoods列表。
     */
    public List<ShowingGoods> show() {
        return goodsMapper.show();
    }

    /**
     * 获取所有店铺信息用于店铺列表展示。
     *
     * @return
     */
    public List<Shops> allShops() {
        return goodsMapper.findAllShops();
    }

    /**
     * 搜索店铺
     *
     * @param sname
     * @return
     */
    public List<Shops> searchShop(String sname) {
        return goodsMapper.searchShops(sname);
    }

    /**
     * 交换online和save的数量
     *
     * @param gid
     */
//    public void updateState(Integer gid,String state){
//        goodsMapper.updateState(gid,state);
//    }
    public void exchange(Integer gid, String state) {
        Goods byGid = goodsMapper.findByGid(gid);
        Integer temp = byGid.getGsave();
        byGid.setGsave(byGid.getGonlinenum());
        byGid.setGonlinenum(temp);
        byGid.setState(state);
        goodsMapper.exchange(byGid);
    }
//    public void exchange(Integer gid){
//        Goods byGid = goodsMapper.findByGid(gid);
//        Integer temp=byGid.getGsave();
//        byGid.setGsave(byGid.getGonlinenum());
//        byGid.setGonlinenum(temp);
//        goodsMapper.exchange(byGid);
//    }

    public List<ShowingGoods> showShop(Integer sid) {
        return goodsMapper.findBySid(sid, "已上架");
    }


    public List<ShowingGoods> searchGoods(String gname) {
        return goodsMapper.searchByName(gname);
    }

}
