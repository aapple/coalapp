package com.dayuzl.coalapp.server.user.service;

import com.dayuzl.coalapp.server.user.domain.Feedback;
import com.dayuzl.coalapp.server.user.domain.User;
import com.dayuzl.coalapp.server.user.repository.FeedbackRepository;
import com.dayuzl.coalapp.server.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public User findByPhone(String phoneNum) {

        logger.info("Get user Info by phone number");

        User user = userRepository.findByPhoneNum(phoneNum);

        return user;
    }

    @Override
    public User createUserAndSave(String PhoneNum) {

        User user = new User(PhoneNum);
        User saveResult = userRepository.save(user);

        return saveResult;
    }

    @Override
    @Transactional
    public User updateUserInfo(User user){

        return userRepository.save(user);
    }

    @Override
    public User findById(int id){
        User result = userRepository.findOne(id);
        return result;
    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
