package com.skyblue.coalapp.server.user.service;


import com.skyblue.coalapp.server.user.domain.User;

/**
 * Created by 张杨 on 2017/5/18.
 */
public interface UserService {

    User findByPhone(String PhoneNum);

    User CreateUserAndSave(String PhoneNum);
}
