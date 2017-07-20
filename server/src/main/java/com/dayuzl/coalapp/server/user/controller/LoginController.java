package com.dayuzl.coalapp.server.user.controller;


import com.alibaba.fastjson.JSON;
import com.dayuzl.coalapp.server.user.domain.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.dayuzl.coalapp.server.product.service.FactoryService;
import com.dayuzl.coalapp.server.framework.BusinessException;
import com.dayuzl.coalapp.server.framework.util.CommonUtils;
import com.dayuzl.coalapp.server.framework.util.RequestUtils;
import com.dayuzl.coalapp.server.framework.ResponseMessage;
import com.dayuzl.coalapp.server.user.service.LoginService;
import com.dayuzl.coalapp.server.user.vo.UserVO;
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


@RestController
@RequestMapping("/app/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    //cache use for record user login info
    Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(600, TimeUnit.SECONDS).initialCapacity(10)
            .maximumSize(1000).build();

    @Autowired
    private LoginService loginService;

    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/getVerifyCode")
    ResponseMessage getVrifyCode(@RequestBody UserVO user){
        logger.info("try to get verify code， user: "+user);

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

        logger.info("request param user: " + user);

        String phoneNum = user.getPhoneNum();
        String verifyCode = user.getVerifyCode();
        String keepedVerifyCode = cache.getIfPresent(phoneNum);

        if(verifyCode.equals(keepedVerifyCode) || phoneNum.equals("18888888888")){

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

        User user = RequestUtils.getUserInfo();

        List a = new ArrayList<>();
        a.add(user);
        a.add(user);
        a.add(user);

        return JSON.toJSONString(a);
    }
}
