package com.example.demo.config;

import com.example.demo.domain.Goods;
import com.example.demo.domain.ShowingGoods;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 配置定时任务类
 */
@Configuration
@EnableScheduling
public class TaskConfig {
    @Autowired
    private GoodsMapper goodsMapper;

    @Scheduled(cron = "0/1 * * * * ?")
    private void updateGoods() {
//        定时上架
        Date date = new Date();
        List<Goods> all = goodsMapper.findAllByTimeNotNull("仓库中");
        for (Goods g : all) {
            if (g.getTime().before(date)) {
                g.setState("已上架");
                g.setTime(null);
                goodsMapper.update(g);
                System.out.println("定时任务：\n\t事件：商品id" + g.getGid() + "上架\n\t执行时间：" + LocalDateTime.now());
            }
        }
//        自动下架
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
