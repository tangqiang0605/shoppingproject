package com.example.demo.mapper;

import com.example.demo.domain.Orders;
import com.example.demo.domain.ShowingCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    /**
     * 查询oders表
     * @param oid
     * @return
     */
    @Select("select * from orders where oid=#{oid}")
    public Orders findOrdersByOid(Integer oid);

    @Select("select * from orders where ostate=#{ostate}")
    public List<Orders> findByState(String ostate);

    @Select("select * from orders where did=#{did}")
    public List<Orders> findByDid(Integer did);

    @Select("select * from orders where cid=#{cid}")
    public List<Orders> findByCid(Integer cid);

    @Select("select * from orders where sid=#{sid}")
    public List<Orders> findBySid(Integer sid);

    @Select("select * from orderson os,goods g where os.oid=#{oid} and os.gid=g.gid")
    public List<ShowingCart> findByOid(Integer oid);

    @Insert("insert into orders values (null,#{ostate},null,#{cid},#{sid},#{isdeli})")
    @Options(useGeneratedKeys = true,keyProperty = "oid",keyColumn = "oid")
    public void insert(Orders orders);

    @Insert("insert into orderson values (#{oid},#{gid},#{oamount})")
    public void insertSon(Integer oid,Integer gid,Integer oamount);

    @Update("update orders set ostate=#{ostate},did=#{did} where oid=#{oid}")
    public void updateDidByOid(Orders orders);

    @Update("update orders set ostate=#{ostate} where oid=#{oid}")
    public void updateOstateByOid(Integer oid,String ostate);
}
