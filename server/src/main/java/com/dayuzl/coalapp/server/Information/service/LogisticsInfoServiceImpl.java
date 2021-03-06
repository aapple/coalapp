package com.dayuzl.coalapp.server.Information.service;

import com.dayuzl.coalapp.server.Information.repository.LogisticsInfoRepository;
import com.dayuzl.coalapp.server.Information.domain.LogisticsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsInfoServiceImpl implements LogisticsInfoService {

    @Autowired
    private LogisticsInfoRepository logisticsInfoRepository;

    @Override
    @CacheEvict(value="logisticsInfoList",allEntries=true)
    public void saveOrUpdate(LogisticsInfo logisticsInfo) {
        logisticsInfoRepository.save(logisticsInfo);
    }

    @Override
    @Cacheable(value = "logisticsInfo", key="#logisticsInfo.toString()",unless="#result!=null")
    public LogisticsInfo findOne(LogisticsInfo logisticsInfo) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<LogisticsInfo> ex = Example.of(logisticsInfo, matcher);

        logisticsInfo = logisticsInfoRepository.findOne(ex);

        return logisticsInfo;
    }

    @Override
    @Cacheable(value = "logisticsInfoList", key="#logisticsInfo.toString()",unless="!(#result.size()>0)")
    public List<LogisticsInfo> findList(LogisticsInfo logisticsInfo){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("departure", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("destination", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<LogisticsInfo> ex = Example.of(logisticsInfo, matcher);
        Sort sort=new Sort(Sort.Direction.DESC,"updatedTime");
        List<LogisticsInfo> logisticsInfos = logisticsInfoRepository.findAll(ex,sort);

        return logisticsInfos;
    }

    @Override
    @CacheEvict(value="logisticsInfoList",allEntries=true)
    public void deleteById(LogisticsInfo logisticsInfo) {
        logisticsInfoRepository.delete(logisticsInfo);
    }

    @CacheEvict(value="logisticsInfoList",allEntries=true)
    @Scheduled(fixedDelay = 2*60*1000)
    public void clearCache(){
    }
}
