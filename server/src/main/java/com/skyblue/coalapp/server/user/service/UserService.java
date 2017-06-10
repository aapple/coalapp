package com.skyblue.coalapp.server.user.service;


import com.skyblue.coalapp.server.user.domain.Feedback;
import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.vo.UserVO;

import java.util.List;

public interface UserService {

    User findByPhone(String PhoneNum);

    User createUserAndSave(String PhoneNum);

    User updateUserInfo(User user);

    User findById(int id);

    List<User> findAll();

    Feedback saveFeedback(Feedback feedback);
}
