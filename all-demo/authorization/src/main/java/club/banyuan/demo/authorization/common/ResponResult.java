package club.banyuan.demo.authorization.common;

import cn.hutool.json.JSONUtil;

public class ResponResult {

    /**
     * code : 200
     * message : 操作成功
     * data : {}
     */

    private int code;
//    private boolean isok;
    private String message;
    private Object data;

    public ResponResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponResult(ResponCode responCode, Object data) {
        this.code = responCode.getCode();
        this.message = responCode.getMessage();
        this.data = data;
    }

    public ResponResult(ResponCode responCode) {
        this.code = responCode.getCode();
        this.message = responCode.getMessage();
    }


    public ResponResult() {
    }




    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }

    public static ResponResult forbidden(){
        return new ResponResult(ResponCode.FORBIDDEN);
    }

    public static ResponResult forbidden(String data){
        return new ResponResult(ResponCode.FORBIDDEN,data);
    }

    public static ResponResult success(Object data){
        return new ResponResult(ResponCode.SUCCESS,data);
    }
    public static ResponResult success(){
        return new ResponResult(ResponCode.SUCCESS);
    }
}
