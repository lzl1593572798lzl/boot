package com.lzl.controller.admin;

import com.lzl.constants.ResponseCode;
import com.lzl.controller.BaseController;
import com.lzl.domain.Obj;
import com.lzl.domain.User;
import com.lzl.http.ResponseData;
import com.lzl.service.UserService;
import com.lzl.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lzl
 * @Date: 2018/08/30 21:36
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseData> insert(@RequestParam("name")String name,
                                               @RequestParam("password")String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Obj obj = new Obj();
        obj.setId(10L);
        user.setObj(obj);
        userService.insert(user);
        System.out.println("-----------------------"+ JsonUtils.toJson(user));
        return renderOk(ResponseCode.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getById(@RequestParam("id")Long id){
        if(null == id || id.longValue() < 0){
            return renderOk(ResponseCode.ILLEGAL_PARAMETER);
        }
        User user = userService.selectById(id);
        if(null == user){
            return renderOk(ResponseCode.ILLEGAL_PARAMETER);
        }
        return renderOk(mapOf("user",user));
    }

}
