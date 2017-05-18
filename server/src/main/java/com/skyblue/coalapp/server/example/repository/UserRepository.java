package com.skyblue.coalapp.server.example.repository;

import com.skyblue.coalapp.server.example.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/18.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByPhoneNum(String phoneNum);

    @Override
    <S extends User> S save(S s);
}