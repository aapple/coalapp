package com.skyblue.coalapp.server.user.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.skyblue.coalapp.server.product.service.FactoryService;
import com.skyblue.coalapp.server.framework.BusinessException;
import com.skyblue.coalapp.server.framework.CommonUtils;
import com.skyblue.coalapp.server.framework.HttpUtils;
import com.skyblue.coalapp.server.framework.ResponseMessage;
import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.service.LoginService;
import com.skyblue.coalapp.server.user.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by bin.yao on 2017/4/12.
 */
@RestController
@RequestMapping("/app/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    //cache use for record user login info
    Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS).initialCapacity(10)
            .maximumSize(1000).build();

    @Autowired
    private LoginService loginService;

    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/getVerifyCode")
    ResponseMessage getVrifyCode(@RequestBody UserVO user){
        logger.info("try to get verify code");

        String phoneNum = user.getPhoneNum();
        String verifyCode = loginService.getVerificationCode(phoneNum);

        if(verifyCode != null){
            cache.put(phoneNum,verifyCode);
            return  ResponseMessage.ok(verifyCode);
        }else{
            return  ResponseMessage.error("message sent failed");
        }
    }

    @RequestMapping("/login")
    String login(@RequestBody UserVO user,
                 HttpServletResponse response){

        logger.info("try to login");

        String phoneNum = user.getPhoneNum();
        String verifyCode = user.getVerifyCode();
        String keepedVerifyCode = cache.getIfPresent(phoneNum);

        if(verifyCode.equals(keepedVerifyCode)){

            User userInfo = loginService.login(phoneNum);
            String userJsonString = JSON.toJSONString(userInfo);
            String base64String = null;
            try {
                base64String = URLEncoder.encode(CommonUtils.getBase64(userJsonString), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new BusinessException("验证异常，请重新尝试");
            }
            Cookie cookie = new Cookie("coalapp_session", base64String);
            cookie.setMaxAge( 365 * 24 * 60 * 60);// 设置为30min
            cookie.setPath("/");
            response.addCookie(cookie);

            return userJsonString;
        }else{
            throw new BusinessException("验证码错误，请重新输入");
        }
    }

    @RequestMapping("/testCookie")
    String testCookie(){

        User user = HttpUtils.getUserInfo();

        List a = new ArrayList<>();
        a.add(user);
        a.add(user);
        a.add(user);

//        throw new BusinessException("网络异常");
        return JSON.toJSONString(a);
    }
}
