package com.lzl.mapper;

import com.lzl.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author: lzl
 * @Date: 2018/08/30 21:24
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,password,obj_id) values(#{user.name},#{user.password},#{user.obj.id})")
    @Options(useGeneratedKeys = true,keyProperty = "user.id",keyColumn = "id")
    Integer insert(@Param("user")User user);


    @Select("select us.id,us.name,us.password,ob.flag as \"obj.flag\",ob.name as \"obj.name\",ob.time as \"obj.time\" from user us left join obj ob on us.obj_id = ob.id where us.id = #{id}")
    User selectById(@Param("id")Long id);

}
