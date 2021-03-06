package com.lzl.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lzl.util.timeutils.LocalDateTimeDeSeri;
import com.lzl.util.timeutils.LocalDateTimeSeri;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 专门处理 json对象的工具类
 * create by lzl ON 2018/07/31
 * @author lzl
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
//      过滤属性值为null的属性
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//       属性命名策略 中间以下划线隔开 首字母小写
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

//      Date对象会自动序列化成时间戳
//      LocalDateTime 不会转换成时间戳
        SimpleModule simpleModule = new SimpleModule();
//        序列化时使用的方案
        simpleModule.addSerializer(LocalDateTime.class,new LocalDateTimeSeri());
//        反序列化时使用的方案
        simpleModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeSeri());
        OBJECT_MAPPER.registerModule(simpleModule);
    }

    public static ObjectMapper getObjectMapper(){
        return OBJECT_MAPPER;
    }

    /**
     * obj -> String
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJson(@NotNull Object object){
        String value = "";
        try {
            value =  OBJECT_MAPPER.writeValueAsString(object);
        }catch (Exception e){
            value = e.getMessage();
        }finally {
            return value;
        }
    }

    /**
     * jsonString -> obj
     * @param json
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T getObjByJsonString(@NotNull String json,@NotNull Class<T> type){
        T t = null;
        try {
            t  = OBJECT_MAPPER.readValue(json,type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 从 obj -> cla
     * @param object
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T convertValue(@NotNull Object object,@NotNull Class<T> cla){
        return  OBJECT_MAPPER.convertValue(object,cla);
    }

}

