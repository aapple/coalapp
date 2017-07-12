package com.dayuzl.coalapp.server.freight.repository;

import com.dayuzl.coalapp.server.freight.domain.FreightInfo;
import com.dayuzl.coalapp.server.trafficInfor.domain.TrafficInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightRepository extends JpaRepository<FreightInfo,Long> {

}
