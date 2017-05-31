package com.skyblue.coalapp.server.framework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by yao on 2017/5/31.
 */
public class ResponseUtils {

    public static String toJSONString(Object object){

        String jsonString = JSON.toJSONStringWithDateFormat(object,
                "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);

        return jsonString;
    }
}
