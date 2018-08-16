package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.cache.service.ObjCache;
import schedule.http.ResponseData;

/**
 * create by lzl ON 2018/08/12
 */
@RestController
@RequestMapping("/cache")
public class RedisCacheController extends BaseController {

    @Autowired
    private ObjCache objCache;

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getObj (@RequestParam("id")Long id){
        return renderOk(objCache.getById(id));
    }

}
