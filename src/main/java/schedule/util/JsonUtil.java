package schedule.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

/**
 * 专门处理 json对象的工具类
 * create by lzl ON 2018/07/31
 * @author lzl
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
//      过滤null
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//      Date对象会自动序列化成时间戳
//      LocalDateTime 不会转换成时间戳
        SimpleModule simpleModule = new SimpleModule();
//        序列化时使用的方案
        simpleModule.addSerializer(LocalDateTime.class,null);
//        反序列化时使用的方案
        simpleModule.addDeserializer(LocalDateTime.class,null);
        OBJECT_MAPPER.registerModule(simpleModule);
    }

    public static ObjectMapper getObjectMapper(){
        return OBJECT_MAPPER;
    }




}

