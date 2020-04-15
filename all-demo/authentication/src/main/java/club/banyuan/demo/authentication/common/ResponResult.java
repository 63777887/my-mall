package club.banyuan.demo.authentication.common;

public class ResponResult {

    /**
     * code : 200
     * message : 操作成功
     * data : {}
     */

    private int code;
    private boolean isok;
    private String message;
    private Object data;

    public ResponResult(int code, boolean isok, String message, Object data) {
        this.code = code;
        this.isok = isok;
        this.message = message;
        this.data = data;
    }

    public ResponResult(int code, boolean isok, String message) {
        this.code = code;
        this.isok = isok;
        this.message = message;
    }

    public ResponResult() {
    }



    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
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

    public static ResponResult error(CustomException e){
        ResponResult responResult = new ResponResult();
        responResult.setIsok(false);
        int code = e.getCode();
        responResult.setCode(code);
        if (code==ResponCode.USER_INPUT_ERROR.getCode()){
            responResult.setMessage(e.getMessage());
        }else if (code==ResponCode.SYSTEM_ERROR.getCode()){
            responResult.setMessage(e.getMessage()+" - - 系统异常");
        }else {
            responResult.setMessage("系统出现未知异常，请联系管理员");
        }
        return responResult;
    }

    public static ResponResult success(Object data){
        return new ResponResult(ResponCode.SUCCESS.getCode(),true,ResponCode.SUCCESS.getMessage(),data);
    }
    public static ResponResult success(){
        return new ResponResult(ResponCode.SUCCESS.getCode(),true,ResponCode.SUCCESS.getMessage());
    }
}
