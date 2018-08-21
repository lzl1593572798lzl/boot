package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.cache.service.ListCache;
import schedule.cache.service.ObjCache;
import schedule.cache.service.StringCache;
import schedule.http.ResponseData;

import java.util.List;

/**
 * create by lzl ON 2018/08/12
 */
@RestController
@RequestMapping("/cache")
public class RedisCacheController extends BaseController {

    @Autowired
    private ObjCache objCache;

    @Autowired
    private StringCache stringCache;

    @Autowired
    private ListCache listCache;

    /**
     * 获取单个对象
     * @param id
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<ResponseData> getObj (@RequestParam("id")Long id){
        return renderOk(mapOf("obj",objCache.getById(id)));
    }

    /**
     * 获取list
     * @param type
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseData> list(@RequestParam("type")Integer type){
        List<String> stringList = listCache.list(type);
        return renderOk(mapOf("list",stringList));
    }

    /**
     * 获取str
     * @param name
     * @return
     */
    @GetMapping("/str")
    public ResponseEntity<ResponseData> str(@RequestParam("str")String name){
        String string = stringCache.get(name);
        return renderOk(mapOf(name,string));
    }

}
