package com.skyblue.coalapp.server.example.controller;

import com.skyblue.coalapp.server.example.service.AccessService;
import com.skyblue.coalapp.server.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bin.yao on 2017/4/12.
 */
@RestController
@RequestMapping("/access")
public class AccessController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccessService accessService;

    @RequestMapping("/login")
    public User get(User user){
        logger.info("ddd");
//        return accessService.login(user);
        return null;
    }
}
