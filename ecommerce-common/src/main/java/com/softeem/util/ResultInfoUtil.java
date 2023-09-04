package com.softeem.util;


import com.softeem.constant.ApiConstant;
import com.softeem.model.common.ResultInfo;

/**
 * 公共返回对象工具类
 */
public class ResultInfoUtil {

    public static <T> ResultInfo<T> buildSuccess(){
        return build(ApiConstant.SUCCESS_CODE,ApiConstant.SUCCESS_MESSAGE,null);
    }

    public static <T> ResultInfo<T> buildSuccess(T data){
        return build(ApiConstant.SUCCESS_CODE,ApiConstant.SUCCESS_MESSAGE,data);
    }

    public static <T> ResultInfo<T> buildError(){
        return build(ApiConstant.ERROR_CODE,ApiConstant.ERROR_MESSAGE,null);
    }

    public static <T> ResultInfo<T> buildError(T data){
        return build(ApiConstant.ERROR_CODE,ApiConstant.ERROR_MESSAGE,data);
    }

    public static <T> ResultInfo<T> buildError(T data,String message){
        return build(ApiConstant.ERROR_CODE,message,data);
    }

    public static <T> ResultInfo<T> build(Integer code,String message,T data){
        if(code ==null){
            code = ApiConstant.SUCCESS_CODE;
        }
        if(message == null){
            message = ApiConstant.SUCCESS_MESSAGE;
        }
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode( code);
        resultInfo.setMessage(message);
        resultInfo.setData(data);
        return resultInfo;
    }
}
