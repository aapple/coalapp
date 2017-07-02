package com.dayuzl.coalapp.server.timeline.repository;

import com.dayuzl.coalapp.server.timeline.domain.Timeline_comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineCommentsRepository extends JpaRepository<Timeline_comments, Long> {

    Timeline_comments findById(Integer id);
}
