package com.lzl.controller;

import com.lzl.domain.Obj;
import com.lzl.http.ResponseData;
import com.lzl.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
        obj.setTime(LocalDateTime.now());
        LOGGER.info("Obj: {}", JsonUtils.toJson(obj));
        return renderOk(mapOf("obj",obj));
    }

    @GetMapping("/exception")
    public ResponseEntity<ResponseData> exception(){
        int  i = 23/0;
        return renderOk();
    }


}
