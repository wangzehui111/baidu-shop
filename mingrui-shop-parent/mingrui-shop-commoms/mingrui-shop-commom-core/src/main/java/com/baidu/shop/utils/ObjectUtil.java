package com.baidu.shop.utils;

/**
 * @ClassName ObjectUtil
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/8/31
 * @Version V1.0
 **/
//查询功能的代码优化工具类
public class ObjectUtil {

    public static Boolean isNull(Object obj){

        return null == obj;
    }

    public static Boolean isNotNull(Object obj){

        return null != obj;
    }

    public static Boolean isEmpty(Object str){

        return null == str || "".equals(str);
    }

    //判断字符串类型不为空且不为null
    public static Boolean isNotEmpty(String str){

        return null != str && !"".equals(str);
    }

}
