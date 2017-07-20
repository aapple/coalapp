package com.dayuzl.coalapp.server.trafficinfo.service;

import com.dayuzl.coalapp.server.trafficinfo.domain.TrafficInfo;
import org.springframework.data.domain.Page;

public interface TrafficInfoService {

    void save(TrafficInfo trafficInfo);

    Page<TrafficInfo> findPage(TrafficInfo traffic);
}
