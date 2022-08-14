package com.example.demo.mapper;

import com.example.demo.domain.ShowingGoods;
import com.example.demo.domain.TestBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {

    @Select("select * from goods,src where goods.srcid=src.srcid and state=\"已上架\"")
    @Results({
            @Result(column = "gid", property = "customer.cid"),
//            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
    })
    public List<TestBean> show();
}
