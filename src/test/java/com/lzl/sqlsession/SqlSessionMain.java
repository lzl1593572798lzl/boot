package com.lzl.sqlsession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * @Author: lzl
 * @Date: 2018/09/12 21:05
 */
public class SqlSessionMain {

    private static SqlSessionFactory sqlSessionFactory;

   public static SqlSession sqlSession(){
       try {
           Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
           sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
           return sqlSessionFactory.openSession();
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
   }

}
