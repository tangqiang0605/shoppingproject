package com.example.demo.mapper;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CartMapper {

//    public List<>

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

    @Select("select sk.sid sid,sname from shoppingcart s,goods g,storekeeper sk where sk.sid=g.sid and cid=#{cid} and g.gid=s.gid group by sid")
    public List<Shops> findShops(Integer cid);

    @Select("select * from shoppingcart s,src,goods g where src.srcid=g.srcid and cid=#{cid} and g.gid=s.gid and sid=#{sid}")
    public List<ShowingCart> findCarts(Integer cid,Integer sid);

    @Delete("delete from shoppingcart where gid=#{gid}")
    public void delByGid(Integer gid);

}
