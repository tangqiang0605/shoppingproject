package com.example.demo.controller;

import com.example.demo.domain.StoreKeeper;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("manager")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 管理员登录验证
     * @param password
     * @return
     */
    @GetMapping("login")
    public Boolean tryLogin(String password){
        return managerService.tryLogin(password);
    }

    /**
     * 获取所有商家
     * @return
     */
    @GetMapping("findallstorekeeper")
    public List<StoreKeeper> findAllStoreKeeper(){return managerService.findAllStoreKeeper();}

    /**
     * 设置商家封禁状态
     * @param storeKeeper
     */
    @PostMapping("updatestorekeeperisban")
    public void updateStoreKeeperIsban(@RequestBody StoreKeeper storeKeeper){
        managerService.updateStoreKeeper(storeKeeper);
    }

}
