package com.softeem.util;


import cn.hutool.core.util.StrUtil;
import com.softeem.constant.ApiConstant;
import com.softeem.exception.ParameterException;

/**
 * 断言工具类
 */
public class AssertUtil {
    /**
     * 判断字符串非空
     * @param str
     * @param message
     * Commons-lang   hutools
     */
    public static void isNotEmpty(String str,String ... message){
        if(StrUtil.isBlank(str)){
            execute(message);
        }
    }

    public static void isNotNull(Object obj,String ... message){
        if(obj == null){
            execute(message);
        }
    }
    public static void isTrue(boolean isTrue,String message){
        if(isTrue){
            execute(message);
        }
    }
    private static void execute(String ... message){
        String msg = ApiConstant.ERROR_MESSAGE;
        if(message!=null && message.length>0){
            msg = message[0];
        }
        throw new ParameterException(msg);
    }
}
