package com.skyblue.coalapp.server.user.repository;

import com.skyblue.coalapp.server.user.domain.Feedback;
import com.skyblue.coalapp.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/18.
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}