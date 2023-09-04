package com.softeem.exception;

import com.softeem.constant.ApiConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterException extends RuntimeException {
    // 错误编码
    private Integer errorCode;

    public ParameterException(){
        super(ApiConstant.ERROR_MESSAGE);
        this.errorCode = ApiConstant.ERROR_CODE;
    }

    public ParameterException(Integer errorCode){
        super(ApiConstant.ERROR_MESSAGE);
        this.errorCode = errorCode;
    }


    public ParameterException(String message){
        super(message);
        this.errorCode = ApiConstant.ERROR_CODE;
    }

    public ParameterException(Integer errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
