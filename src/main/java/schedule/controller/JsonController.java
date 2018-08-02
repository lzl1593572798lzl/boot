package schedule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.domain.Obj;
import schedule.http.ResponseData;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * create by lzl ON 2018/08/01
 */
@RestController
@RequestMapping("/json")
public class JsonController extends BaseController {

    @GetMapping("/get")
    public ResponseEntity<ResponseData> get(){
        Obj obj = new Obj();
        obj.setName("大头鬼");
        obj.setDate(new Date());
        obj.setTime(LocalDateTime.now());
        return renderOk(obj);
    }

}
