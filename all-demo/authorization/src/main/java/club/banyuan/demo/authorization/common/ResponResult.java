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

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }


    public static ResponResult success(Object data){
        return new ResponResult(ResponCode.SUCCESS,data);
    }

    public static ResponResult success(){
        return new ResponResult(ResponCode.SUCCESS);
    }

    public static ResponResult failed(Object data) {
        return new ResponResult(ResponCode.SYSTEM_ERROR, data);
    }

    public static ResponResult failed() {
        return new ResponResult(ResponCode.SYSTEM_ERROR, "未知错误");
    }

    public static ResponResult loginFailed(Object data) {
        return new ResponResult(ResponCode.LOGIN_FAIL, data);
    }

    public static ResponResult unauthorized(Object data) {
        return new ResponResult(ResponCode.UNAUTHORIZED, data);
    }

    public static ResponResult unauthorized() {
        return new ResponResult(ResponCode.UNAUTHORIZED, ResponCode.UNAUTHORIZED.getMessage());
    }


    public static ResponResult badRequest(Object data) {
        return new ResponResult(ResponCode.BAD_REQUEST, data);
    }


    public static ResponResult forbidden(){
        return new ResponResult(ResponCode.FORBIDDEN);
    }

    public static ResponResult forbidden(String data){
        return new ResponResult(ResponCode.FORBIDDEN,data);
    }


}
