package com.skyblue.coalapp.server.timeline.repository;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.timeline.domain.Timeline_videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Repository
public interface TimelineVideosRepository extends JpaRepository<Timeline_videos, Long> {

}
