package schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.domain.Obj;
import schedule.http.ResponseData;
import schedule.util.JsonUtil;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * create by lzl ON 2018/08/01
 * @author lzl
 */
@RestController
@RequestMapping("/json/test")
public class JsonController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonController.class);

    @GetMapping("/get")
    public ResponseEntity<ResponseData> get() throws Exception{
        Obj obj = new Obj();
        obj.setName("大头鬼");
        obj.setDate(new Date());
        obj.setTime(LocalDateTime.now());
        LOGGER.info("Obj: {}", JsonUtil.writeAsString(obj));
        return renderOk(obj);
    }

    @GetMapping("/exception")
    public ResponseEntity<ResponseData> exception(){
        int  i = 23/0;
        return renderOk();
    }


}
