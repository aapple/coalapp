package com.dayuzl.coalapp.server.trafficInfor.service;

import com.dayuzl.coalapp.server.trafficInfor.domain.TrafficInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrafficInfoService {

    void saveOrUpdate(TrafficInfo trafficInfo);

    Page<TrafficInfo> findList(TrafficInfo trafficInfo);
}
