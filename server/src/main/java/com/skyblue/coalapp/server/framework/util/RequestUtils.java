package com.skyblue.coalapp.server.framework.util;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.framework.util.CommonUtils;
import com.skyblue.coalapp.server.user.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by yao on 2017/5/21.
 */
public class RequestUtils {

    public static User getUserInfo(){

        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if ( null == cookies || cookies.length == 0) {
            return null;
        }

        for(Cookie cookie : cookies){
            if(StringUtils.equals("coalapp_session", cookie.getName())){

                try {
                    String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    String userInfoJsonString = CommonUtils.getFromBase64(value);
                    return JSON.parseObject(userInfoJsonString, User.class);
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
