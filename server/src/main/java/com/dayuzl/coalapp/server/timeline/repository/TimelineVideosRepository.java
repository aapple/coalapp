package com.dayuzl.coalapp.server.timeline.repository;

import com.dayuzl.coalapp.server.timeline.domain.Timeline_videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineVideosRepository extends JpaRepository<Timeline_videos, Long> {

}
