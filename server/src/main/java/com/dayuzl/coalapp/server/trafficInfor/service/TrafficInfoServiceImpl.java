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
    @CacheEvict(value="dailyNewsList",allEntries=true)
    public void saveOrUpdate(TrafficInfo trafficInfo) {
        trafficInfoRepository.save(trafficInfo);
    }

    @Override
    @Cacheable(value = "dailyNewsList", key="#pageNumber",unless="!(#result!=null)")
    public Page<TrafficInfo> findList(Integer pageNumber){

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageRequest = new PageRequest(pageNumber, 10, sort);

        Page<TrafficInfo> trafficInfos = trafficInfoRepository.findAll(pageRequest);

        return trafficInfos;
    }
}
