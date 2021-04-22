package com.upc.system.exception;

import com.upc.system.config.CodeMsg;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @SuppressWarnings("unchecked")
    @ExceptionHandler(value = Exception.class)
    public CodeMsg<Object> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();//打印异常信息

        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return ex.getCodeMsg();
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            //这里假设只返回一个error
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return CodeMsg.BIND_ERROR.fillArgs(msg);
        } else {
            return CodeMsg.SERVER_ERROR;
        }
    }

}