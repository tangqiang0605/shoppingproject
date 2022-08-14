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

    @Select("select s.sid,s.sname,sum(gsales) totalsales,count(gid) onlinegoodsnum\n" +
            "    from storekeeper as s,goods\n" +
            "    where s.sid=goods.sid\n" +
            "    group by s.sid;")
    public List<Shops> findAllShops();

    @Select("select * from goods")
    public List<Goods> findAll();

    //    查的字段多于类的属性会不会出问题啊，而这个类本来就少。不会。
    @Select("select * from storekeeper,goods,src where goods.srcid=src.srcid and state=\"已上架\" and storekeeper.sid=goods.sid")
    @Results({
            @Result(column = "srcurl", property = "srcurl"),
            @Result(column = "sname",property = "sname"),
            @Result(column = "gid", property = "goods.gid"),
//            @Result(column = "",property = "goods.sid"),
            @Result(column = "gsales", property = "goods.gsales"),
            @Result(column = "gname", property = "goods.gname"),
            @Result(column = "gonlinenum", property = "goods.gonlinenum"),
    })
    public List<ShowingGoods> show();

    @Insert("insert into goods values(null,#{gsave},#{gsales},#{sid},#{state},#{srcid},#{gname},#{gonlinenum},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "gid", keyColumn = "gid")
    public void insert(Goods goods);

}
