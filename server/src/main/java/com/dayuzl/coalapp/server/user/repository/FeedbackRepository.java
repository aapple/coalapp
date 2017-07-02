package com.dayuzl.coalapp.server.user.repository;

import com.dayuzl.coalapp.server.user.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}