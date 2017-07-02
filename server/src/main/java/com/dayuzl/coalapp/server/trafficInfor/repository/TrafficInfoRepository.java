package com.dayuzl.coalapp.server.trafficInfor.repository;

import com.dayuzl.coalapp.server.trafficInfor.domain.TrafficInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficInfoRepository extends JpaRepository<TrafficInfo,Long> {

}
