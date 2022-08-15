package com.example.demo.controller;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Goods;
import com.example.demo.domain.StoreKeeper;
import com.example.demo.service.GoodsService;
import com.example.demo.service.StoreKeeperService;
import org.apache.catalina.Store;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("storekeeper")
public class StoreKeeperController {
    @Autowired
    private StoreKeeperService storeKeeperService;


    /**
     * 账号管理：注册
     * @param storeKeeper
     * @return
     */
    @PostMapping("signup")
    public Integer signUp(@RequestBody StoreKeeper storeKeeper){
        return storeKeeperService.signUp(storeKeeper);
//        注册成功返回账号id，提醒用户记住。
    }

    /**
     * 账号管理：登录
     * @param storeKeeper
     * @return
     */
    @PostMapping("login")
    public String tryLogin(@RequestBody StoreKeeper storeKeeper){
        return storeKeeperService.tryLogin(storeKeeper);
    }
    @PostMapping("getinf")
    public StoreKeeper getInf(@RequestBody StoreKeeper storeKeeper){
        return storeKeeperService.getInf(storeKeeper);
    }

    /**
     * 账号管理：修改（店主入口）
     * 注：账号封禁功能在管理员处。
     * @param storeKeeper
     * @return
     */
    @PostMapping("update")
    public String update(@RequestBody StoreKeeper storeKeeper){
//        在前端的提交前检测是否用户名和密码是没有变的。
        storeKeeperService.update(storeKeeper);
        return "更新成功";
    }



    @PostMapping("addgoods/base")
    public int addGoodsBase(@RequestBody Goods goods){
        return storeKeeperService.addGoods(goods);
    }





}
