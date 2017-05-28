package com.skyblue.coalapp.server.Information.service;

import com.skyblue.coalapp.server.Information.domain.LogisticsInfo;
import com.skyblue.coalapp.server.Information.repository.LogisticsInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsInfoServiceImpl implements LogisticsInfoService {

    @Autowired
    private LogisticsInfoRepository logisticsInfoRepository;

    @Override
    public void saveOrUpdate(LogisticsInfo logisticsInfo) {
        logisticsInfoRepository.save(logisticsInfo);
    }

    @Override
    public LogisticsInfo findOne(LogisticsInfo logisticsInfo) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<LogisticsInfo> ex = Example.of(logisticsInfo, matcher);

        logisticsInfo = logisticsInfoRepository.findOne(ex);

        return logisticsInfo;
    }

    @Override
    public List<LogisticsInfo> findList(LogisticsInfo logisticsInfo) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("departure", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("destination", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<LogisticsInfo> ex = Example.of(logisticsInfo, matcher);

        List<LogisticsInfo> logisticsInfos = logisticsInfoRepository.findAll(ex);

        return logisticsInfos;
    }
}
