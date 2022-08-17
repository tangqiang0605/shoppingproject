package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.SrcMapper;
import com.example.demo.mapper.StoreKeeperMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreKeeperService {

    @Autowired
    private SrcMapper srcMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StoreKeeperMapper storeKeeperMapper;

    /**
     * 店主操作，添加商品到goods表中。
     *
     * @param goods
     * @return
     */
    public int add(Goods goods) {
        goodsMapper.insert(goods);
        return goods.getGid();
    }



    public Integer signUp(StoreKeeper storeKeeper){
        storeKeeperMapper.insert(storeKeeper);
        return storeKeeper.getSid();
    }
    public String tryLogin(StoreKeeper storeKeeper){
        StoreKeeper s = storeKeeperMapper.findById(storeKeeper.getSid());
        if (s==null){
            return "用户不存在";
        }
        if(!s.getSpassword().equals(storeKeeper.getSpassword())){
            return "密码错误";
        }
        if (s.getIsban()){
            return "账号封禁中";
        }
        return "登录验证成功";
    }
    public StoreKeeper getInf(StoreKeeper storeKeeper){
        StoreKeeper s=storeKeeperMapper.findById(storeKeeper.getSid());
        if (s==null||(!s.getSpassword().equals(storeKeeper.getSpassword()))){
            return null;
        }
        return s;
    }

    public int update(StoreKeeper storeKeeper){
        storeKeeperMapper.update(storeKeeper);
        return storeKeeper.getSid();
    }

    public int addGoods(Goods goods){
        goodsMapper.insert(goods);
        return goods.getGid();
    }

    public List<ShowingGoods> findGoodsBySid(Integer sid){
        return goodsMapper.findBySid(sid,"已上架");
    }

    public List<ShowingGoods> findRepository(Integer sid){
        return goodsMapper.findBySid(sid,"仓库中");
    }


//    public void delGoods(Integer gid){
//
//    }

//    public List<Goods> find()

//    public List<Src> findAllSrc(){
//        return
//    }

}
