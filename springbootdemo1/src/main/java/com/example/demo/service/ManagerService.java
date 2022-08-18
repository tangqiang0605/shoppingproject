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

    public Boolean tryLogin(String password) {
        return mypassword.equals(password);
    }

    public List<StoreKeeper> findAllStoreKeeper() {
        return storeKeeperMapper.findAll();
    }

    public void updateStoreKeeper(StoreKeeper storeKeeper) {
        storeKeeperMapper.update(storeKeeper);
    }
}
