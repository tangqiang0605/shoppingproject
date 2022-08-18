package com.example.demo.mapper;

import com.example.demo.domain.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerMapper {
    @Insert("insert into customer values(null,#{cname},#{cpassword})")
    @Options(useGeneratedKeys = true,keyProperty = "cid",keyColumn = "cid")
    public void insert(Customer customer);

    @Select("select * from customer where cid=#{cid}")
    public Customer findById(Integer cid);

    @Update("update customer set cname=#{cname},cpassword=#{cpassword}")
    public void update(Customer customer);
}
