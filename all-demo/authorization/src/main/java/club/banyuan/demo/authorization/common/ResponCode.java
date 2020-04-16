package club.banyuan.demo.authorization.common;

public enum  ResponCode {
    SUCCESS(200,"操作成功"),
    FORBIDDEN(403,"用户未授权"),
    USER_INPUT_ERROR(400,"用户输入异常"),
    SYSTEM_ERROR (500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");
    private final int code;
    private final String message;

    ResponCode(int code, String message) {
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
