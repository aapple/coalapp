package com.dayuzl.coalapp.spider.modules.traffic.repository;

import com.dayuzl.coalapp.spider.modules.traffic.domain.TrafficInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficInfoRepository extends JpaRepository<TrafficInfo, Long> {

}
