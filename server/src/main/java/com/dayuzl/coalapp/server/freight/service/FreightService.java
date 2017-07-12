package com.dayuzl.coalapp.server.freight.service;

import com.dayuzl.coalapp.server.freight.domain.FreightInfo;
import com.dayuzl.coalapp.server.trafficInfor.domain.TrafficInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FreightService {

    void addOrUpdate(FreightInfo freightInfo);

    List<FreightInfo> findList(FreightInfo freightInfo);

    void delete(FreightInfo freightInfo);
}
