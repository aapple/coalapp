package com.skyblue.coalapp.server.repsonse;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.example.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yao on 2017/5/21.
 */
public class HttpUtils {

    public static User getUserInfo(){

        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if ( null == cookies || cookies.length == 0) {
            return null;
        }

        for(Cookie cookie : cookies){
            if(StringUtils.equals("coalapp_session", cookie.getName())){
                String userInfoJsonString = CommonUtils.getFromBase64(cookie.getValue());
                return JSON.parseObject(userInfoJsonString, User.class);
            }
        }
        return null;
    }
}
