package com.dayuzl.coalapp.server.user.controller;

import com.alibaba.fastjson.JSON;
import com.dayuzl.coalapp.server.user.domain.User;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.product.service.FactoryService;
import com.dayuzl.coalapp.server.framework.BusinessException;
import com.dayuzl.coalapp.server.framework.util.CommonUtils;
import com.dayuzl.coalapp.server.framework.util.RequestUtils;
import com.dayuzl.coalapp.server.user.domain.Feedback;
import com.dayuzl.coalapp.server.user.service.UserService;
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
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/app/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/update")
    public String update(@RequestBody User user, HttpServletResponse response){

        logger.debug("update user info, request param user : " + user);

        User userInfo = RequestUtils.getUserInfo();
        user.setId(userInfo.getId());
        User newUserInfo = userService.updateUserInfo(user);

        // 更新cookie
        String userJsonString = JSON.toJSONString(newUserInfo);
        String base64String = null;
        try {
            base64String = URLEncoder.encode(CommonUtils.getBase64(userJsonString), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException("用户信息更新异常，请重新尝试");
        }

        Cookie cookie = new Cookie("coalapp_session", base64String);
        cookie.setMaxAge(365 * 24 * 60 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);

        logger.debug("user info update success, response user : " + userJsonString);

        return userJsonString;
    }

    @RequestMapping("/my-info")
    public String myinfo(HttpServletResponse response){

        User userInfo = RequestUtils.getUserInfo();

        if(userInfo == null) {
            return "";
        }

        User newUserInfo = userService.findById(userInfo.getId());
        // 更新cookie
        String userJsonString = JSON.toJSONString(newUserInfo);
        String base64String = null;
        try {
            base64String = URLEncoder.encode(CommonUtils.getBase64(userJsonString), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        Cookie cookie = new Cookie("coalapp_session", base64String);
        cookie.setMaxAge( 365 * 24 * 60 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);

        return userJsonString;
    }

    @RequestMapping("/getUserList")
    public String getUserList(){

        List<User> userList = userService.findAll();

        return ResponseUtils.toJSONString(userList);
    }

    @RequestMapping("/feedback")
    String feedback(@RequestBody Feedback feedback){

        User userInfo = RequestUtils.getUserInfo();
        feedback.setCreated_at(new Date());
        feedback.setUser(userInfo);
        feedback.setPhoneNum(userInfo.getPhoneNum());
        feedback.setUserName(userInfo.getUserName());
        feedback = userService.saveFeedback(feedback);

        return "非常感谢您的积极反馈，反馈已经提交成功，如有必要我们随后会与您联系！";
    }
}