package schedule.util.timeutils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * localDateTime反序列化类
 * create by lzl ON 2018/08/02
 * @author lzl
 */
public class LocalDateTimeDeSeri extends StdDeserializer<LocalDateTime> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeDeSeri.class);

    public LocalDateTimeDeSeri(){
        this(null);
    }

    protected LocalDateTimeDeSeri(Class<LocalDateTime> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return TimeUtils.timeStampToTime(p.getLongValue());
        }catch (Exception e){
            LOGGER.error("反序列化失败。longValue:{}",p.getLongValue(),e);
            return null;
        }
    }
}
