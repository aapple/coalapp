package com.skyblue.coalapp.server.Information.service;

import com.skyblue.coalapp.server.Information.domain.LogisticsInfo;

import java.util.List;

/**
 * 物流信息Service
 */
public interface LogisticsInfoService {

    void saveOrUpdate(LogisticsInfo logisticsInfo);

    LogisticsInfo findOne(LogisticsInfo logisticsInfo);

    List<LogisticsInfo> findList(LogisticsInfo logisticsInfo);
}
