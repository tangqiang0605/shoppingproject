package com.example.demo.mapper;

import com.example.demo.domain.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CartMapper {

    @Select("select * from shoppingcart where cid=#{cid} and gid=#{gid}")
    public Cart findRecord(Cart cart);

    @Update("update shoppingcart set oamount=#{oamount} where cid=#{cid} and gid=#{gid}")
    public void updateRecord(Cart cart);

    @Insert("insert into shoppingcart values(#{cid},#{gid},#{oamount})")
    public void insert(Cart cart);

    @Select("select * from shoppingcart")
    public List<Cart> findAll();

    @Select("select * from shoppingcart,goods where cid=#{cid} and shoppingcart.gid=goods.gid group by gid")
    public List<Cart> findByCid(Integer cid);

}
