package com.example.demo.config;

import com.example.demo.domain.Goods;
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
    private void updateGoods(){
        Date date=new Date();
        List<Goods> all = goodsMapper.findAllByTimeNotNull("仓库中");
        for (Goods g:all) {
            if("仓库中".equals(g.getState())&&g.getTime()!=null){
                if(g.getTime().before(date)){
                    g.setState("已上架");
                    g.setTime(null);
                    goodsMapper.update(g);
					System.out.println("定时任务：\n\t事件：商品id"+g.getGid()+"上架\n\t执行时间："+LocalDateTime.now());
                }
            }
        }
    }

}
