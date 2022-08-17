package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingGoods;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        // TODO: 2022/8/17 销量是在售商品的总销量。
        return goodsMapper.findAllShops("已上架");
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
    public void setGoodsTime(Integer gid, Long time){
//        String strTime = "1660807830000";
//        Long longTime = Long.parseLong(strTime);
        if(time==0||time==null){
//            goodsService.setGoodsTime();
            goodsMapper.updateTimeByGid(gid,null);
        }else{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(time);
        Date newTime = null;
        try {
            newTime = formatter.parse(dateString);
        } catch (ParseException e) {
//            e.printStackTrace();
            System.out.println("GoodsService.setGoodsTime报错啦！！！");
        }
        goodsMapper.updateTimeByGid(gid,newTime);
        }
    }

    public List<ShowingGoods> showShop(Integer sid) {
        return goodsMapper.findBySid(sid, "已上架");
    }


    public List<ShowingGoods> searchGoods(String gname) {
        return goodsMapper.searchByName(gname, "已上架");
    }

}
