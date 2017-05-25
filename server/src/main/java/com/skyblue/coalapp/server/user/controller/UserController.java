package com.skyblue.coalapp.server.user.controller;

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
@RequestMapping("/app/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping("/update")
    String update(@RequestBody UserVO user){
        logger.info("try to get verify code");

        String phoneNum = user.getPhoneNum();
        String verifyCode = loginService.getVerificationCode(phoneNum);

        return "更新成功";
    }

}
