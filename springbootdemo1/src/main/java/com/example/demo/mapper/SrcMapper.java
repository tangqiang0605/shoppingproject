package com.example.demo.mapper;

import com.example.demo.domain.Src;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SrcMapper {

    @Insert("insert into src values(#{srcid},#{srcurl},#{srcname})")
    @Options(useGeneratedKeys = true,keyColumn = "srcid",keyProperty = "srcid")
    public void insert(Src src);

//    @Select("select * from src")
//    public List<Src> findAll();
}
