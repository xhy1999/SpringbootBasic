package com.xhy.common.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.xhy.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 异常捕捉
 * @author xuehy
 * @since 2021/5/8
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 自定义异常捕捉
     */
    @ExceptionHandler(value = CustomException.class)
    public Result CustomExceptionHandler(CustomException e) {
        Result r = new Result();
        r.put("code", e.getCode());
        r.put("message", e.getMsg());
        return r;
    }

    /**
     * 错误的Http请求方式异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result requestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        String msg = e.getMessage();
        Result r = new Result();
        r.put("code", 400);
        r.put("message", msg);
        return r;
    }

    /**
     * 处理参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        String error = "";
        for (ObjectError objectError : errorList) {
            error = objectError.getDefaultMessage();
        }
        Result r = new Result();
        r.put("code", 400);
        r.put("message", error);
        return r;
    }

    /**
     * 处理@Validated —— @RequestParam的参数异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        String msg = e.getMessage();
        msg = msg.substring(msg.lastIndexOf(":") + 1).trim();
        Result r = new Result();
        r.put("code", 400);
        r.put("message", msg);
        return r;
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ExceptionHandler(InvalidFormatException.class)
    public Result invalidFormatExceptionHandler(InvalidFormatException e) {
        String msg = e.getMessage();
        msg = msg.replaceAll("\"", "'");
        Result r = new Result();
        r.put("code", 400);
        r.put("message", msg.substring(msg.indexOf(":") + 2, msg.indexOf(";")));
        //截取message关键部分,返回
        return r;
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ExceptionHandler(MismatchedInputException.class)
    public Result mismatchedInputExceptionHandler(MismatchedInputException e) {
        String msg = e.getLocalizedMessage();
        msg = msg.replaceAll("\"", "'");
        Result r = new Result();
        r.put("code", 400);
        r.put("message", msg);
        //截取message关键部分,返回
        return r;
    }

    /**
     * 处理"Required request body is missing"异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        String msg = e.getLocalizedMessage();
        if (msg.contains(":")) {
            msg = msg.substring(0, msg.indexOf(":"));
        }
        msg = msg.replaceAll("\"", "'");
        Result r = new Result();
        r.put("code", 400);
        r.put("message", msg);
        return r;
    }

    /**
     * 处理"Required Integer parameter 'xxx' is not present"异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        String msg = e.getLocalizedMessage();
        Result r = new Result();
        r.put("code", 400);
        r.put("message", msg);
        return r;
    }

    /**
     * 处理"Maximum upload size exceeded"异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result MaxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e) {
        Result r = new Result();
        r.put("code", 400);
        r.put("message", "文件大小超过上限");
        return r;
    }

    /**
     * 处理"Maximum upload size exceeded"异常
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result HttpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        Result r = new Result();
        r.put("code", 400);
        r.put("message", "不支持的Content-Type");
        return r;
    }

    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e) {
        log.error("捕捉到异常:");
        log.error(e.getMessage(), e);
        Result r = new Result();
        r.put("code", 500);
        r.put("message", e.getMessage());
        return r;
    }

}
