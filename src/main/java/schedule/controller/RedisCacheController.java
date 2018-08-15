package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedule.domain.Obj;
import schedule.http.ResponseData;
import schedule.service.ObjCacheService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * create by lzl ON 2018/08/12
 */
@RestController
@RequestMapping("/cache")
public class RedisCacheController extends BaseController {

    @Autowired
    private ObjCacheService objCacheService;


    @PostMapping("/add")
    public ResponseEntity<ResponseData> cacheTest(@RequestParam("id")Long id){
        Obj obj = new Obj();
        obj.setId(id);
        obj.setTime(LocalDateTime.now());
        obj.setDate(new Date());
        obj.setFlag(true);
        Obj objNew =  objCacheService.getObjById(obj);
        return renderOk(objNew);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getObj (@RequestParam("id")Long id) throws Exception{
        List<Obj> str =  objCacheService.getById(id);
        return renderOk(str);
    }

    @GetMapping("/get_str")
    public ResponseEntity<ResponseData> getStr(@RequestParam("key")String key){
        return renderOk(objCacheService.getString(key));
    }

    @GetMapping("/set_str")
    public ResponseEntity<ResponseData> setStr(@RequestParam("key")String key){
        objCacheService.setString(key);
        return renderOk();
    }

}
