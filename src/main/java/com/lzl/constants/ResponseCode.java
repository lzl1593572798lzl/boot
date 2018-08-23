package com.lzl.constants;

/**
 * create by lzl ON 2018/08/23
 */
public enum ResponseCode {

    OK(2000,"请求处理成功"),

    FAIL(5000,"处理失败"),
    ILLEGAL_PARAMETER (50001,"非法参数"),
    ;

    ResponseCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
