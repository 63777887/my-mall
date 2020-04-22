package club.banyuan.zgMallMgt.advice;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponseResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 拦截异常并统一处理
 */
@RestControllerAdvice
public class RequestExceptionHandler {

    //全局异常捕捉处理
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult runtimeExceptionHandler(RuntimeException ex) {
        return ResponseResult.failed(ex.getMessage());
    }

    //参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult ReqFailExceptionHandler(MethodArgumentNotValidException ex) {

        return ResponseResult.badRequest(ex.getMessage());
    }

    @ExceptionHandler(ReqFailException.class)
    public ResponseResult LoginFail(ReqFailException ex) {
        return ResponseResult.loginFailed(ex.getMessage());

    }
}
