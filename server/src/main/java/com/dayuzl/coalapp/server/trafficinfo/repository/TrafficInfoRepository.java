package com.dayuzl.coalapp.server.trafficinfo.repository;

import com.dayuzl.coalapp.server.trafficinfo.domain.TrafficInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficInfoRepository extends JpaRepository<TrafficInfo,Long> {

}
