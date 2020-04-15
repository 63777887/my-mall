package club.banyuan.demo.authentication.common;

public class CustomException extends RuntimeException{

    //异常错误编码
    private int code ;
    //异常信息
    private String message;

    private CustomException(){}

    public CustomException(ResponCode responCode, String message) {
        this.code = responCode.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
