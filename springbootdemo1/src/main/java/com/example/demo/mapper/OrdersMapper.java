package com.example.demo.mapper;

import com.example.demo.domain.Orders;
import com.example.demo.domain.ShowingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {

    @Select("select * from orders")
    public List<Orders> findAll();

    @Select("select * from orderson os,goods g where os.oid=#{oid} and os.gid=g.gid")
    public List<ShowingCart> findByOid(Integer oid);

    @Select("select * from orders where cid=#{cid}")
    public List<Orders> findByCid(Integer cid);

    @Insert("insert into orders values (null,#{ostate},null,#{cid},#{sid},#{isdeli})")
    @Options(useGeneratedKeys = true,keyProperty = "oid",keyColumn = "oid")
    public void insert(Orders orders);

    @Insert("insert into orderson values (#{oid},#{gid},#{oamount})")
    public void insertSon(Integer oid,Integer gid,Integer oamount);
}
