package com.lzl.mapper;

import com.lzl.domain.Obj;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lzl
 * @Date: 2018/08/23 21:59
 */
/**
 *  引用开启二级缓存配置
 */
//@CacheNamespaceRef(ObjMapper.class)
public interface ObjMapper {

//    @Select("select * from obj where id =#{id}")
//    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Obj getById(@Param("id")Long id);

    /**
     * 插入数据
     * @param obj
     * @return
     */
    @Insert("insert into obj(name,flag,time,age,score,salary,my_address) values(#{obj.name}," +
            "#{obj.flag},#{obj.time},#{obj.age},#{obj.score},#{obj.salary},#{obj.myAddress})")
    @Options(useGeneratedKeys = true,keyProperty = "obj.id",keyColumn = "id")
    Integer insert(@Param("obj")Obj obj);

    @Update("update obj set deleted = #{deleted} where id = #{id}")
    Integer updateDeletedById(@Param("id")Long id,
                              @Param("deleted")Boolean deleted);

    @Select("select * from obj where deleted = 0 and name = #{name}")
    List<Obj> list(@Param("name")String name);

    List<Obj> listByLikeName(@Param("name")String name);

    /**
     *
     * @param obj
     * @return
     */
    Integer insertToDb(@Param("obj")Obj obj);

    List<Obj> selectByName(@Param("name")String name,@Param("age")Integer age);

    Integer updateByObj(@Param("obj")Obj obj);

    /**
     * 根据id做 in 查询
     * @param idList
     * @return
     */
    List<Obj> selectByList(@Param("id_list")List<Integer> idList);

    /**
     * 根据map查询
     * @param objMap
     * @return
     */
    List<Obj> selectByMap(@Param("obj_map")Map<Long,Obj> objMap);

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertByList(@Param("list")List<Obj> list);

    /**
     * 测试使用bind语法
     * @param name
     * @return
     */
    List<Obj> selectUseBind(@Param("name")String name);



}
