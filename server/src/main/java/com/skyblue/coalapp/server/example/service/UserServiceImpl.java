package com.skyblue.coalapp.server.example.service;

import com.skyblue.coalapp.server.example.domain.User;
import com.skyblue.coalapp.server.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 张杨 on 2017/5/18.
 */
@Service
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByPhone(String phoneNum) {

        logger.info("Get user Info by phone number");

        User user = userRepository.findByPhoneNum(phoneNum);

        return user;
    }

    @Override
    public User CreateUserAndSave(String PhoneNum) {

        User user = new User(PhoneNum);

        User saveResult = userRepository.save(user);

        return saveResult;
    }
}
