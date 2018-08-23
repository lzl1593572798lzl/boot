package com.lzl.controller.admin;

import com.lzl.cache.service.ObjCache;
import com.lzl.constants.ResponseCode;
import com.lzl.controller.BaseController;
import com.lzl.domain.Obj;
import com.lzl.http.ResponseData;
import com.lzl.service.ObjService;
import com.lzl.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @Author: lzl
 * @Date: 2018/08/23 22:16
 */
@RestController
@RequestMapping("/admin/obj")
public class ObjController extends BaseController {

    @Autowired
    private ObjService objService;

    @Autowired
    private ObjCache objCache;

    @PostMapping("/save_obj")
    public ResponseEntity<ResponseData> saveObj(@RequestParam("name")String name,
                                                @RequestParam("age")Integer age,
                                                @RequestParam("flag")Boolean flag,
                                                @RequestParam("score")Double score,
                                                @RequestParam("salary")Long salary,
                                                @RequestParam("my_address")String myAddress){
        Obj obj = new Obj();
        obj.setName(name);
        obj.setAge(age);
        obj.setFlag(flag);
        obj.setTime(LocalDateTime.now());
        obj.setScore(score);
        obj.setSalary(salary);
        obj.setMyAddress(myAddress);
        obj.setDeleted(false);
        obj.setCreatedAt(obj.getTime());
        obj.setUpdatedAt(obj.getCreatedAt());
        objService.insert(obj);
        System.out.println(JsonUtils.toJson(obj));
        objCache.saveCache(obj);
        return renderOk();
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getById(@RequestParam("id")Long id){
        Obj obj = objService.get(id);
        return renderOk(mapOf("obj",((null == obj) ? "" : obj)));
    }

    @PostMapping("/deleted")
    public ResponseEntity<ResponseData> deletedById(@RequestParam("id")Long id){
        Obj obj = objService.get(id);
        if(null == obj){
            return renderOk(ResponseCode.ILLEGAL_PARAMETER);
        }else {
            objService.removeById(id);
        }
        return renderOk();
    }

}
