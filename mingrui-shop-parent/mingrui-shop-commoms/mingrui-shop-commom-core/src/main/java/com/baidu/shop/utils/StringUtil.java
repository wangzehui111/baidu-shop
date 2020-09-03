package com.baidu.shop.utils;

/**
 * @ClassName StringUtil
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/9/1
 * @Version V1.0
 **/
public class StringUtil {

    //判断字符串类型为空或null
    public static Boolean isEmpty(String str){

        return null == str || "".equals(str);
    }

    //判断字符串类型不为空切不为null
    public static Boolean isNtoEmpty(String str){

        return null != str && !"".equals(str);
    }

    public static Integer toInteger(String str){

        if(isNtoEmpty(str)) return Integer.parseInt(str);
        return 0;
    }
}
