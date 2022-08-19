package com.example.demo.config;

import com.example.demo.task.GoodsTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 配置定时任务类
 */
@Configuration
@EnableScheduling
public class TaskConfig {
    @Autowired
    private GoodsTask goodsTask;

    @Scheduled(cron = "0/1 * * * * ?")
    private void updateGoods() {
//        定时上架
        goodsTask.upload();
//        自动下架
        goodsTask.download();
    }
}
