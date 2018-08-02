package schedule.util.timeutils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * create by lzl ON 2018/08/01
 * @author lzl
 */
public class LocalDateTimeSeri extends StdSerializer<LocalDateTime> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeSeri.class);

    public LocalDateTimeSeri(){
        this(null);
    }

    public LocalDateTimeSeri(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            gen.writeNumber(TimeUtils.timeToTimestampForMillSecond(value).toString());
        }catch (Exception e){
            LOGGER.error("序列化时间报错。time:{}",value,e);
        }
    }


}
