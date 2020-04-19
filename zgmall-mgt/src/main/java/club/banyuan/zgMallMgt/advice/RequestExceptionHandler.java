package club.banyuan.zgMallMgt.advice;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 拦截异常并统一处理
 */
@RestControllerAdvice
public class RequestExceptionHandler {

    //全局异常捕捉处理
//    @ExceptionHandler(ReqFailException.class)
//    public ResponResult runtimeExceptionHandler(RuntimeException ex) {
//
//        return ResponResult.failed(ex.getMessage());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponResult runtimeExceptionHandler(MethodArgumentNotValidException ex) {

        return ResponResult.badRequest(ex.getMessage());
    }

    @ExceptionHandler(ReqFailException.class)
    public ResponResult LoginFail(ReqFailException ex) {
        return ResponResult.loginFailed(ex.getMessage());
    }


}
