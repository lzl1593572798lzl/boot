package com.lzl.mapper;

import com.lzl.domain.Obj;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: lzl
 * @Date: 2018/08/23 21:59
 */
@Mapper
public interface ObjMapper {

    @Select("select * from obj where id =#{id}")
    Obj getById(@Param("id")Long id);

    @Insert("insert into obj(name,flag,time,age,score,salary,my_address) values(#{obj.name}," +
            "#{obj.flag},#{obj.time},#{obj.age},#{obj.score},#{obj.salary},#{obj.myAddress})")
    @Options(useGeneratedKeys = true,keyProperty = "obj.id",keyColumn = "id")
    Integer insert(@Param("obj")Obj obj);

    @Update("update obj set deleted = #{deleted} where id = #{id}")
    Integer updateDeletedById(@Param("id")Long id,
                              @Param("deleted")Boolean deleted);

    @Select("select * from obj where deleted = 0 and name = #{name}")
    List<Obj> list(@Param("name")String name);

}
