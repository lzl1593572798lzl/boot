package schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import schedule.http.ResponseData;

/**
 * create by lzl ON 2018/07/31
 */
public class BaseController {

    protected ResponseEntity<ResponseData> renderStatus(Integer status){
        return render(status,"");
    }

    protected ResponseEntity<ResponseData> renderOk(){
        return renderOk("");
    }

    protected ResponseEntity<ResponseData> renderOk(Object data){
        return render(200,data);
    }

    protected ResponseEntity<ResponseData> render(Integer status,Object data){
        return assemble(status,data);
    }

    private ResponseEntity<ResponseData> assemble(Integer status,Object data){
        ResponseData responseData = new ResponseData(status,data);
        ResponseEntity<ResponseData> responseEntity = new ResponseEntity<>(responseData,HttpStatus.OK);
        return responseEntity;
    }


}
