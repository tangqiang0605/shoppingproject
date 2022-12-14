package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.OrdersMapper;
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
    private OrdersMapper ordersMapper;

    public void updateGoods(Integer gid, String state) {
        Goods byGid = goodsMapper.findByGid(gid);
        if(state.equals("已上架")||state.equals("仓库中")){
            Integer temp = byGid.getGsave();
            byGid.setGsave(byGid.getGonlinenum());
            byGid.setGonlinenum(temp);
        }
        byGid.setState(state);
        goodsMapper.update(byGid);
    }

    public void setGoodsTime(Integer gid, Long time){
        if(time==0||time==null){
            goodsMapper.updateTimeByGid(gid,null);
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(time);
            Date newTime = null;
            try {
                newTime = formatter.parse(dateString);
            } catch (ParseException e) {
                System.out.println("GoodsService.setGoodsTime报错啦！！！");
            }
            goodsMapper.updateTimeByGid(gid,newTime);
        }
    }

    public List<ShowingCart> getOrderson(Integer oid) {
        return ordersMapper.findByOid(oid);
    }

    public List<ShowingGoods> searchGoods(String gname) {
        return goodsMapper.searchByName(gname, "已上架");
    }
    /**
     * 获取店铺
     * 销量是在售商品的总销量。
     * @param sname
     * @return
     */
    public List<Shops> searchShop(String sname) {
        return goodsMapper.searchShops(sname,"已上架");
    }
    public List<ShowingGoods> showShop(Integer sid) {
        return goodsMapper.selectByNamePlus("", "已上架",sid);
    }

}
