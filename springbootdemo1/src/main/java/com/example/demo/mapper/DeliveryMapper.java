package com.example.demo.mapper;

import com.example.demo.domain.Delivery;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
@RestController
public interface DeliveryMapper {

    @Insert("insert into delivery values (null,#{dname},#{dpassword})")
    @Options(useGeneratedKeys = true,keyColumn = "did",keyProperty = "did")
    public void insert(Delivery delivery);

    @Select("select * from delivery")
    public List<Delivery> findAll();

    @Update("update delivery set dname=#{dname},dpassword=#{dpassword} where did=#{did}")
    public void update(Delivery delivery);
    @Select("select * from delivery where did=#{did}")
    public Delivery findById(Integer did);
}
