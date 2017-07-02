package com.dayuzl.coalapp.server.timeline.repository;

import com.dayuzl.coalapp.server.timeline.domain.Timeline_imgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineImgsRepository extends JpaRepository<Timeline_imgs, Long> {

}
