package com.example.demo.task;

import com.example.demo.domain.Goods;
import com.example.demo.domain.ShowingGoods;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class GoodsTask {
    @Autowired
    private GoodsMapper goodsMapper;

    public void upload() {
        Date date = new Date();
        List<Goods> all = goodsMapper.findAllByTimeNotNull("仓库中");
        for (Goods g : all) {
            if (g.getTime().before(date)) {
                g.setState("已上架");
                Integer temp = g.getGonlinenum();
                g.setGonlinenum(g.getGsave());
                g.setGsave(temp);
                g.setTime(null);
                goodsMapper.update(g);
                System.out.println("定时任务：\n\t事件：商品id" + g.getGid() + "上架\n\t执行时间：" + LocalDateTime.now());
            }
        }
    }

    public void download() {
        List<ShowingGoods> showingGoods = goodsMapper.searchByName("", "已上架");
        for (ShowingGoods s : showingGoods) {
            if (s.getGoods().getGonlinenum() == 0) {
                s.getGoods().setState("仓库中");
                goodsMapper.update(s.getGoods());
                System.out.println("定时任务：\n\t事件：商品id" + s.getGoods().getGid() + "下架\n\t执行时间：" + LocalDateTime.now());
            }
        }
    }
}
