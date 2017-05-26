package com.skyblue.coalapp.server.user.repository;

import com.skyblue.coalapp.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPhoneNum(String phoneNum);

    @Override
    <S extends User> S save(S s);

    @Modifying
    @Query("update User set avatar= ?1 where id = ?2")
    int updateAvatar(String avatar, int id);

    @Modifying
    @Query("update User set nickname = ?1 where id = ?2")
    int updateNickname(String nickname, int id);

    @Modifying
    @Query("update User set gender = ?1 where id = ?2")
    int updateGender(int gender, int id);

    @Modifying
    @Query("update User set bio = ?1 where id = ?2")
    int updateBio(String bio, int id);
}