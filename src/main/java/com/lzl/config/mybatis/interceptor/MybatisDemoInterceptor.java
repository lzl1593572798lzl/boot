package com.lzl.config.mybatis.interceptor;

import com.lzl.util.JsonUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * 测试mybatis的拦截器
 * @Author: lzl
 * @Date: 2018/09/16 16:24
 */
@Intercepts(
        {
                @Signature(type = Executor.class,method = "query",
                        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class,method = "query",
                        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
                @Signature(type = ResultSetHandler.class,method = "handleResultSets",
                        args = {Statement.class})
        })
@Component
public class MybatisDemoInterceptor implements Interceptor {

    private static  final String RESULT_SET = "handleResultSets";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        String methodName = method.getName() ;
        printLn("methodName: ", methodName);
        printLn("args : ", invocation.getArgs());
        printLn("target: ", invocation.getTarget());
        List<Object>  objectList ;
        if(RESULT_SET.equals(methodName)){
            objectList = (List<Object>)invocation.proceed();
            for(Object obj : objectList){
                printLn("obj : ", obj);
            }
            Statement statement = (Statement)invocation.getArgs()[0];
            printLn("statement: ",statement);
        }else {
            objectList = (List<Object>) invocation.proceed();
            MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
            String nameSpace = mappedStatement.getId();
            printLn("nameSpace: ",nameSpace);
        }
        return objectList;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 输出
     * @param key
     * @param object
     */
    private void printLn(String key,Object object){
        System.out.println(key+" : "+JsonUtils.toJson(object));
    }
}
