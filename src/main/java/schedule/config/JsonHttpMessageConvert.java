package schedule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import schedule.util.JsonUtil;

import java.util.List;

/**
 * create by lzl ON 2018/08/01
 * @author lzl
 */
@Configuration
public class JsonHttpMessageConvert implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        使用统一的json 序列化方案
        converter.setObjectMapper(JsonUtil.getObjectMapper());
        converters.add(0,converter);
    }

}
