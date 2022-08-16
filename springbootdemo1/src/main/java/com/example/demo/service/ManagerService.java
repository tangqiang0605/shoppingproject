package com.example.demo.service;

import com.example.demo.domain.StoreKeeper;
import com.example.demo.mapper.StoreKeeperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Value("${mypassword}")
    private String mypassword;

    @Autowired
    private StoreKeeperMapper storeKeeperMapper;
//    private StoreKeeperService storeKeeperService;

    public Boolean tryLogin(String password){
//        System.out.println(mypassword);
//        System.out.println(password);
        if(mypassword.equals(password)){
            return true;
        }
        return false;
    }

    public List<StoreKeeper> findAllStoreKeeper(){
        return storeKeeperMapper.findAll();
    }

    public void updateStoreKeeper(StoreKeeper storeKeeper){
//        System.out.println(storeKeeper);
        storeKeeperMapper.update(storeKeeper);
    }
}
