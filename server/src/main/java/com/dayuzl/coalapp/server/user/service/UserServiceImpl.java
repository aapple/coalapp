package com.dayuzl.coalapp.server.user.service;

import com.dayuzl.coalapp.server.framework.BusinessException;
import com.dayuzl.coalapp.server.user.domain.Feedback;
import com.dayuzl.coalapp.server.user.domain.User;
import com.dayuzl.coalapp.server.user.repository.FeedbackRepository;
import com.dayuzl.coalapp.server.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    //find User
    @Override
    public User findUser(User user) {

        logger.info("Get user Info");

        List<User> userList = this.getList(user);

        User userInfo = null;
        if(userList != null && userList.size()>0){
            userInfo = userList.get(0);
        }

        return userInfo;
    }

    @Override
    public List<User> getList(User user) {

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<User> ex = Example.of(user, matcher);

        //查询
        List<User> userList = userRepository.findAll(ex);

        return userList;
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
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
