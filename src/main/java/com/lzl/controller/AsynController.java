package com.lzl.controller;

import com.lzl.http.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * 测试异步调用
 * create by lzl ON 2018/08/13
 */
@RestController
@RequestMapping("/async")
public class AsynController extends BaseController{

    @GetMapping("/test")
    public ResponseEntity<ResponseData> asyncTest(){
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return renderOk();
    }

    @GetMapping("/sync")
    public ResponseEntity<ResponseData> sync(@RequestParam("url")String url){
        RestTemplate restTemplate = new RestTemplate();
        String obj =  restTemplate.getForObject(url,String.class);
        System.out.println(obj);
        return renderOk();
    }

    @GetMapping("/async")
    public ResponseEntity<ResponseData>  async(@RequestParam("url")String url){
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        ListenableFuture<ResponseEntity<String>> future =  asyncRestTemplate.getForEntity(url,String.class);
        future.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步调用报错: "+throwable.getMessage());
            }

            @Override
            public void onSuccess(@Nullable ResponseEntity<String> stringResponseEntity) {
                System.out.println("异步调用结束: "+stringResponseEntity.toString());
            }
        });
        return renderOk();
    }


}
