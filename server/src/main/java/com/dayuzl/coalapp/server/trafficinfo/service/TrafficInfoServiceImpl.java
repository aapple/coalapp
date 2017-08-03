package com.dayuzl.coalapp.server.trafficinfo.service;

import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import com.dayuzl.coalapp.server.trafficinfo.domain.TrafficInfo;
import com.dayuzl.coalapp.server.trafficinfo.repository.TrafficInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TrafficInfoServiceImpl implements TrafficInfoService {

    @Autowired
    private TrafficInfoRepository trafficInfoRepository;

    @Override
    @CacheEvict(value="trafficInfoCache",allEntries=true)
    public void save(TrafficInfo trafficInfo) {
        trafficInfoRepository.save(trafficInfo);
    }

    @Override
    @Cacheable(value = "trafficInfoCache", key="#traffic",unless="!(#result!=null)")
    public Page<TrafficInfo> findPage(TrafficInfo traffic){

        Example<TrafficInfo> ex = this.buildExample(traffic);

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");

        Pageable pageRequest = new PageRequest(traffic.checkPageNumber(), traffic.checkPageSize(), sort);

        Page<TrafficInfo> trafficInfos = trafficInfoRepository.findAll(ex,pageRequest);

        return trafficInfos;
    }

    private Example<TrafficInfo> buildExample(TrafficInfo traffic){

        ExampleMatcher matcher = ExampleMatcher.matching()//构建对象
                .withMatcher("traffic.departure", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("traffic.destination", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        return Example.of(traffic, matcher);
    }

    @CacheEvict(value="trafficInfoCache",allEntries=true)
    @Scheduled(fixedDelay = 2*60*1000)
    public void clearCache(){
    }
}
