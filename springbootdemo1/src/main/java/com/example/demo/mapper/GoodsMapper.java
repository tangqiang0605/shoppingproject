package com.example.demo.mapper;

import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingGoods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    @Select("select gid from goods where sid=#{sid}")
    public List<Integer> findByShop(Integer sid);

    @Select("select s.sid,s.sname,sum(gsales) totalsales,count(gid) onlinegoodsnum\n" +
            "    from storekeeper as s,goods\n" +
            "    where s.sid=goods.sid\n" +
            "    group by s.sid;")
    public List<Shops> findAllShops();

    @Select("select s.sid,s.sname,sum(gsales) totalsales,count(gid) onlinegoodsnum\n" +
            "    from storekeeper as s,goods\n" +
            "    where s.sid=goods.sid and s.sname like concat('%',#{sname},'%')\n" +
            "    group by s.sid;")
    public List<Shops> searchShops(String sname);

    @Select("select * from goods")
    public List<Goods> findAll();

    //    查的字段多于类的属性会不会出问题啊，而这个类本来就少。不会。
    @Select("select * from storekeeper,goods,src where goods.srcid=src.srcid and state=\"已上架\" and storekeeper.sid=goods.sid")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname", property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
//            @Result(column = "",property = "goods.sid"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
    })
    public List<ShowingGoods> show();

    @Select("select * from storekeeper,goods,src \n" +
            "where goods.srcid=src.srcid \n" +
            "  and state=\"已上架\" \n" +
            "  and storekeeper.sid=goods.sid\n" +
            "  and storekeeper.sid=#{sid}")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname", property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
//            @Result(column = "",property = "goods.sid"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
    })
    public List<ShowingGoods> findBySid(Integer sid);


    //    模糊查询
//    @Select("select * from t_user where gname like concat('%',#{gname},'%')")
    @Select("select * from storekeeper,goods,src \n" +
            "where goods.srcid=src.srcid \n" +
            "  and state=\"已上架\" \n" +
            "  and storekeeper.sid=goods.sid\n" +
            "  and goods.gname like concat('%',#{gname},'%')")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname", property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
//            @Result(column = "",property = "goods.sid"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
    })
    public List<ShowingGoods> searchByName(String gname);

    @Insert("insert into goods values(null,#{gsave},#{gsales},#{sid},#{state},#{srcid},#{gname},#{gonlinenum},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "gid", keyColumn = "gid")
    public void insert(Goods goods);

}
