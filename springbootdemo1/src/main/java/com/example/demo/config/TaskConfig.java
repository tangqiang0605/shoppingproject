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

@Configuration
@EnableScheduling
public class TaskConfig {
    @Autowired
    private GoodsMapper goodsMapper;

//    @Scheduled(cron = "* * * * * ? *")
    @Scheduled(cron = "0/1 * * * * ?")
    private void updateGoods(){
        Date date=new Date();
        List<Goods> all = goodsMapper.findAll();
        for (Goods g:all) {
            if("仓库中".equals(g.getState())&&g.getTime()!=null){
                if(g.getTime().before(date)){
                    goodsMapper.updateState(g.getGid(),"已上架");
                    goodsMapper.updateTimeByGid(g.getGid(),null);
					System.out.println("定时任务：\n\t事件：商品id"+g.getGid()+"上架\n\t执行时间："+LocalDateTime.now());
                }
            }
        }

//        System.out.println("执行定时任务 " + LocalDateTime.now());
    }

//    @Configuration  //标记配置类
// 2 @EnableScheduling   //开启定时任务
// 3 public class MyScheduleConfig {
// 4
//         5     //添加定时任务
//         6     @Scheduled(cron = "0/5 * * * * ?")
// 7     private void myTasks() {
//            8         System.out.println("执行定时任务 " + LocalDateTime.now());
//            9     }
//10 }
}
