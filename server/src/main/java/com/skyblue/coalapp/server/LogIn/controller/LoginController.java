package com.skyblue.coalapp.server.LogIn.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.skyblue.coalapp.server.LogIn.service.LoginService;
import com.skyblue.coalapp.server.LogIn.vo.UserVO;
import com.skyblue.coalapp.server.example.domain.User;
import com.skyblue.coalapp.server.example.service.AccessService;
import com.skyblue.coalapp.server.repsonse.BusinessException;
import com.skyblue.coalapp.server.repsonse.CommonUtils;
import com.skyblue.coalapp.server.repsonse.HttpUtils;
import com.skyblue.coalapp.server.repsonse.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

            String base64String = CommonUtils.getBase64(userJsonString);
            Cookie cookie = new Cookie("coalapp_session", base64String);
            cookie.setMaxAge( 365 * 24 * 60 * 60);// 设置为30min
            cookie.setPath("/");
            response.addCookie(cookie);

            return userJsonString;
        }else{

            throw new BusinessException("登录失败");
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
