package com.softeem.advice;

import com.softeem.model.common.ResultInfo;
import com.softeem.util.ResultInfoUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle  {


    @ExceptionHandler(Exception.class)
    public ResultInfo<String> exceptionHandle(Exception ex){
        ex.printStackTrace();
        return ResultInfoUtil.buildError(ex.getMessage());
    }
}
