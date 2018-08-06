package schedule.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常的捕获类
 * create by lzl ON 2018/08/02
 * @author lzl
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e){
        System.out.println("................................................................");
        return e.toString();
    }

}