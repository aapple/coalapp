package com.skyblue.coalapp.server.user.controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.framework.ResponseUtils;
import com.skyblue.coalapp.server.product.domain.ProductPrice;
import com.skyblue.coalapp.server.product.service.FactoryService;
import com.skyblue.coalapp.server.framework.BusinessException;
import com.skyblue.coalapp.server.framework.CommonUtils;
import com.skyblue.coalapp.server.framework.RequestUtils;
import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.service.UserService;
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
import java.util.List;

/**
 * Created by bin.yao on 2017/4/12.
 */
@RestController
@RequestMapping("/app/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/update")
    String update(@RequestBody UserVO user, HttpServletResponse response){

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
        cookie.setMaxAge( 365 * 24 * 60 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);

        return userJsonString;
    }

    @RequestMapping("/my-info")
    String myinfo(HttpServletResponse response){

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

}
