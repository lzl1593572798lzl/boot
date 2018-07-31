package schedule.http;

/**
 * create by lzl ON 2018/07/31
 */
public class ResponseData {

    private Integer code;

    private Object data;

    public ResponseData() {
        this.code = 0;
    }

    public ResponseData(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
