package com.dayuzl.coalapp.spider.modules.traffic.repository;

import com.dayuzl.coalapp.spider.modules.traffic.domain.TrafficInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TrafficInfoRepository extends JpaRepository<TrafficInfo, Long> {

    @Modifying
    @Transactional
    Long deleteBySpiderFlag(@Param("spiderFlag") Integer spiderFlag);
}
