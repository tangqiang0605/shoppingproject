package com.example.demo.mapper;

import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingGoods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    @Delete("delete from goods where gid=#{gid}")
    public void delByGid(Integer gid);

    @Update("update goods set state=#{state} where gid=#{gid}")
    public void updateState(Integer gid,String state);

    @Update("update goods set gsave=#{gsave},gsales=#{gsales},state=#{state},srcid=#{srcid},gonlinenum=#{gonlinenum} where gid=#{gid}")
    public void exchange(Goods goods);

    @Select("select * from goods where gid=#{gid}")
    public Goods findByGid(Integer gid);

    @Select("select gid from goods where sid=#{sid}")
    public List<Integer> findByShop(Integer sid);

    @Select("select s.sid,s.sname,sum(gsales) totalsales,count(gid) onlinegoodsnum\n" +
            "    from storekeeper as s,goods\n" +
            "    where s.sid=goods.sid and goods.state=#{state} and s.sname like concat('%',#{sname},'%')\n" +
            "    group by s.sid;")
    public List<Shops> searchShops(String sname,String state);

    @Select("select * from goods")
    public List<Goods> findAll();

    @Select("select * from storekeeper,goods,src \n" +
            "where goods.srcid=src.srcid \n" +
            "  and state=#{state} \n" +
            "  and storekeeper.sid=goods.sid\n" +
            "  and goods.gname like concat('%',#{gname},'%')")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname", property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
            @Result(column = "state",property = "goods.state"),
            @Result(column = "gsave",property = "goods.gsave"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "sid", property = "goods.sid"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
            @Result(column = "time", property = "goods.time"),
    })
    public List<ShowingGoods> searchByName(String gname,String state);

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
            @Result(column = "state",property = "goods.state"),
            @Result(column = "gsave",property = "goods.gsave"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "sid", property = "goods.sid"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
            @Result(column = "time", property = "goods.time"),
    })
    public List<ShowingGoods> searchByNamePlus(String gname,String state,Integer sid);

    @Insert("insert into goods values(null,#{gsave},#{gsales},#{sid},#{state},#{srcid},#{gname},#{gonlinenum},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "gid", keyColumn = "gid")
    public void insert(Goods goods);

    @Update("update goods set time=#{time} where gid=#{gid}")
    public void updateTimeByGid(Integer gid,Date time);
}
