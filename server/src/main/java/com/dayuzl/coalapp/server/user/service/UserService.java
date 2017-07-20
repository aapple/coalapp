package com.dayuzl.coalapp.server.user.service;


import com.dayuzl.coalapp.server.user.domain.Feedback;
import com.dayuzl.coalapp.server.user.domain.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    User createUserAndSave(String PhoneNum);

    User updateUserInfo(User user);

    User findUser(User user);

    List<User> getList(User user);

    Feedback saveFeedback(Feedback feedback);
}
