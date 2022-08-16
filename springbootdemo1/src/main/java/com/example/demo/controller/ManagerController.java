package com.example.demo.controller;

import com.example.demo.domain.StoreKeeper;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("manager")
@RestController
public class ManagerController {
//    todo：注释
//    todo：配送员
//    todo：管理员
    // TODO: 2022/8/16 空页提示
    @Autowired
    private ManagerService managerService;

    @GetMapping("login")
    public Boolean tryLogin(String password){
        return managerService.tryLogin(password);
    }

    @GetMapping("findallstorekeeper")
    public List<StoreKeeper> findAllStoreKeeper(){return managerService.findAllStoreKeeper();}

    @PostMapping("updatestorekeeperisban")
    public void updateStoreKeeperIsban(@RequestBody StoreKeeper storeKeeper){
        managerService.updateStoreKeeper(storeKeeper);
    }

}
