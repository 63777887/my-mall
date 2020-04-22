package club.banyuan.zgMallMgt.common;

public enum ResponseCode {
    SUCCESS(200,"操作成功"),
    BAD_REQUEST(400, "请求失败"),
    UNAUTHORIZED(401, "信息错误"),
    FORBIDDEN(403, "权限错误"),
    SYSTEM_ERROR (500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");
    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
