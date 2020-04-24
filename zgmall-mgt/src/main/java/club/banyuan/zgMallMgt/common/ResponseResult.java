package club.banyuan.zgMallMgt.common;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import static club.banyuan.zgMallMgt.common.FailReason.UMS_ADMIN_BAD_TOKEN;

public class ResponseResult {


    /**
     * code : 200
     * message : 操作成功
     * data : {}
     */

    private int code;
//    private boolean isok;
    private String message;
    private Object data;

    public ResponseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public ResponseResult(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ResponseResult() {
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


    public static ResponseResult success(Object data){
        return new ResponseResult(ResponseCode.SUCCESS,data);
    }

    public static ResponseResult success(){
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    public static ResponseResult failed(Object data) {
        return new ResponseResult(ResponseCode.SYSTEM_ERROR, data);
    }

    public static ResponseResult failed() {
        return new ResponseResult(ResponseCode.SYSTEM_ERROR, "未知错误");
    }

    public static ResponseResult loginFailed(Object data) {
        return new ResponseResult(ResponseCode.UNAUTHORIZED, data);
    }

    public static ResponseResult unauthorized(Object data) {
        return new ResponseResult(ResponseCode.UNAUTHORIZED, data);
    }

    public static ResponseResult unauthorized() {
        return new ResponseResult(ResponseCode.UNAUTHORIZED, UMS_ADMIN_BAD_TOKEN);
    }


    public static ResponseResult badRequest(Object data) {
        return new ResponseResult(ResponseCode.BAD_REQUEST, data);
    }


    public static ResponseResult forbidden(){
        return new ResponseResult(ResponseCode.FORBIDDEN);
    }

    public static ResponseResult forbidden(String data){
        return new ResponseResult(ResponseCode.FORBIDDEN,data);
    }



}
