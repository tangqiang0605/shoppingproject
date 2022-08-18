package com.example.demo.mapper;

import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingGoods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author taipanlang
 */
@Mapper
@Repository
public interface GoodsMapper {

    /**
     * 添加商品
     *
     * @param goods
     */
    @Insert("insert into goods values(null,#{gsave},#{gsales},#{sid},#{state},#{srcid},#{gname},#{gonlinenum},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "gid", keyColumn = "gid")
    void insert(Goods goods);

    /**
     * 修改商品1
     * 可以和前端直接匹配的数据
     * @param goods
     */
    @Update("update goods set gsave=#{gsave},gsales=#{gsales},state=#{state},srcid=#{srcid},gonlinenum=#{gonlinenum},time=#{time} where gid=#{gid}")
    void update(Goods goods);

    /**
     * 修改商品2
     * 这个和更新商品分开,这里的service处理的是前端传来数据不能自动匹配的.
     * @param gid
     * @param time
     */
    @Update("update goods set time=#{time} where gid=#{gid}")
    void updateTimeByGid(Integer gid, Date time);


    @Select("select * from goods where gid=#{gid}")
    Goods findByGid(Integer gid);

    /**
     * 用于顾客获取订单的
     * @param sid
     * @return
     */
    @Select("select gid from goods where sid=#{sid}")
    List<Integer> findByShop(Integer sid);

    @Select("select s.sid,s.sname,sum(gsales) totalsales,count(gid) onlinegoodsnum\n" +
            "    from storekeeper as s,goods\n" +
            "    where s.sid=goods.sid and goods.state=#{state} and s.sname like concat('%',#{sname},'%')\n" +
            "    group by s.sid;")
    List<Shops> searchShops(String sname, String state);

    @Select("select * from goods where time is not null and state=#{state}")
    List<Goods> findAllByTimeNotNull(String state);

    /**
     * 这个是顾客用的,包括所有商家.plus是给商家用的,查询条件增加了商家id
     * @param gname
     * @param state
     * @return
     */
    @Select("select * from storekeeper,goods,src \n" +
            "where goods.srcid=src.srcid \n" +
            "  and state=#{state} \n" +
            "  and storekeeper.sid=goods.sid\n" +
            "  and goods.gname like concat('%',#{gname},'%')")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname", property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
            @Result(column = "gsave", property = "goods.gsave"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "sid", property = "goods.sid"),
            @Result(column = "state", property = "goods.state"),
            @Result(column = "srcid", property = "goods.srcid"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
            @Result(column = "time", property = "goods.time"),
    })
    List<ShowingGoods> searchByName(String gname, String state);

    @Select("select * from storekeeper,goods,src \n" +
            "where goods.srcid=src.srcid \n" +
            "  and state=#{state} \n" +
            "  and storekeeper.sid=goods.sid\n" +
            "  and storekeeper.sid=#{sid}\n" +
            "  and goods.gname like concat('%',#{gname},'%')")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname", property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
            @Result(column = "gsave", property = "goods.gsave"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "sid", property = "goods.sid"),
            @Result(column = "state", property = "goods.state"),
            @Result(column = "srcid", property = "goods.srcid"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
            @Result(column = "time", property = "goods.time"),
    })
    List<ShowingGoods> selectByNamePlus(String gname, String state, Integer sid);

}
