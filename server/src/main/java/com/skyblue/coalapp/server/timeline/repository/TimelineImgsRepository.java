package com.skyblue.coalapp.server.timeline.repository;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.timeline.domain.Timeline_imgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineImgsRepository extends JpaRepository<Timeline_imgs, Long> {

}
