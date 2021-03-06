package com.lzl.controller;

import com.google.common.collect.ImmutableMap;
import com.lzl.constants.ResponseCode;
import com.lzl.http.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * create by lzl ON 2018/07/31
 * @author lzl
 */
public class BaseController {

    protected ResponseEntity<ResponseData> renderOk(){
        return renderOk(ResponseCode.OK);
    }

    protected ResponseEntity<ResponseData> renderOk(Map<String,Object> stringObjectMap){
        return render(200,stringObjectMap);
    }

    private ResponseEntity<ResponseData> render(Integer status,Object data){
        return assemble(status,data);
    }

    protected ResponseEntity<ResponseData> renderOk(ResponseCode responseCode){
        return assemble(responseCode.getCode(),responseCode.getMessage());
    }

    private ResponseEntity<ResponseData> assemble(Integer status,Object data){
        ResponseData responseData = new ResponseData(status,data);
        ResponseEntity<ResponseData> responseEntity = new ResponseEntity<>(responseData,HttpStatus.OK);
        return responseEntity;
    }

    protected Map<String,Object> mapOf(String keyOne,Object valueOne){
        return ImmutableMap.of(keyOne,valueOne);
    }

    protected Map<String,Object> mapOf(String keyOne,Object valueOne,String keyTwo,Object valueTwo){
        return ImmutableMap.of(keyOne,valueOne,keyTwo,valueTwo);
    }

    protected Map<String,Object> mapOf(String keyOne,Object valueOne,String keyTwo,Object valueTwo,String keyThree,Object valueThree){
        return ImmutableMap.of(keyOne,valueOne,keyTwo,valueTwo,keyThree,valueThree);
    }

    protected Map<String,Object> mapOf(String keyOne,Object valueOne,String keyTwo,Object valueTwo,String keyThree,
                                       Object valueThree,String keyFour,Object valueFour){
        return ImmutableMap.of(keyOne,valueOne,keyTwo,valueTwo,keyThree,valueThree,keyFour,valueFour);
    }


    protected Map<String,Object> mapOf(String keyOne,Object valueOne,String keyTwo,Object valueTwo,String keyThree,
                                       Object valueThree,String keyFour,Object valueFour,String keyFive,Object valueFive ){
        return ImmutableMap.of(keyOne,valueOne,keyTwo,valueTwo,keyThree,valueThree,keyFour,valueFour,keyFive,valueFive);
    }


}
