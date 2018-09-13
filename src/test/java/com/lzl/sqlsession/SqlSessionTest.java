package com.lzl.sqlsession;

import com.lzl.domain.Obj;
import com.lzl.mapper.ObjMapper;
import com.lzl.util.JsonUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author: lzl
 * @Date: 2018/09/12 22:22
 */
public class SqlSessionTest {

    /**
     * 测试一级缓存
     */
    private static void testOneLevelCache(){
        try {
            SqlSession sqlSession = SqlSessionMain.sqlSession();
            ObjMapper objMapper = sqlSession.getMapper(ObjMapper.class);
            Obj obj = objMapper.getById(2L);
            System.out.println(JsonUtils.toJson(obj));
            obj.setName(" new  ");
            Obj newObj = objMapper.getById(2L);
            System.out.println(JsonUtils.toJson(newObj));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testOneLevelCache();
    }

}
