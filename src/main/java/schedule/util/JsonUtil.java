package schedule.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 专门处理 json对象的工具类
 * create by lzl ON 2018/07/31
 * @author lzl
 */
public class JsonUtil {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
//      过滤null
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public static ObjectMapper getObjectMapper(){
        return OBJECT_MAPPER;
    }

}
