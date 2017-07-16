package com.dayuzl.coalapp.server.user.service;


import com.dayuzl.coalapp.server.user.domain.Feedback;
import com.dayuzl.coalapp.server.user.domain.User;

import java.util.List;

public interface UserService {

    User createUserAndSave(String PhoneNum);

    User updateUserInfo(User user);

    User findUser(User user);

    List<User> findAll();

    Feedback saveFeedback(Feedback feedback);
}
