package com.skyblue.coalapp.server.user.service;


import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.vo.UserVO;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/18.
 */
public interface UserService {

    User findByPhone(String PhoneNum);

    User createUserAndSave(String PhoneNum);

    User updateUserInfo(UserVO user);

    User findById(int id);

    List<User> findAll();
}
