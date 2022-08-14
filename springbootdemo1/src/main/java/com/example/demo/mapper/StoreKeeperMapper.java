package com.example.demo.mapper;

import com.example.demo.domain.Customer;
import com.example.demo.domain.StoreKeeper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoreKeeperMapper {
    @Insert("insert into storekeeper values(null,#{sname},#{spassword},false)")
    @Options(useGeneratedKeys = true,keyProperty = "sid",keyColumn = "sid")
    public void insert(StoreKeeper storeKeeper);

    @Select("select * from storekeeper where sid=#{sid}")
    public StoreKeeper findById(Integer sid);

    @Update("update storekeeper set sname=#{sname},spassword=#{spassword},isban=#{isban} where sid=#{sid}")
    public void update(StoreKeeper storeKeeper);

    @Select("select * from storekeeper")
    public List<StoreKeeper> findAll();

}
