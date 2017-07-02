package com.dayuzl.coalapp.server.trafficInfor.service;

import com.dayuzl.coalapp.server.trafficInfor.domain.TrafficInfo;
import com.dayuzl.coalapp.server.trafficInfor.repository.TrafficInfoRepository;
import com.dayuzl.coalapp.server.trafficInfor.service.TrafficInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficInfoServiceImpl implements TrafficInfoService {

    @Autowired
    private TrafficInfoRepository trafficInfoRepository;

    @Override
    @CacheEvict(value="trafficInfoList",allEntries=true)
    public void saveOrUpdate(TrafficInfo trafficInfo) {
        trafficInfoRepository.save(trafficInfo);
    }

    @Override
    @Cacheable(value = "trafficInfoList", key="#trafficInfo.toString()",unless="!(#result.size()>0)")
    public Page<TrafficInfo> findList(TrafficInfo trafficInfo){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("departure", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("destination", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<TrafficInfo> ex = Example.of(trafficInfo, matcher);

        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageRequest = new PageRequest(0, 10, sort);

        Page<TrafficInfo> trafficInfos = trafficInfoRepository.findAll(pageRequest);

        return trafficInfos;
    }
}
