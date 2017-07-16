package com.dayuzl.coalapp.server.freight.service;

import com.dayuzl.coalapp.server.freight.domain.FreightInfo;
import com.dayuzl.coalapp.server.freight.repository.FreightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreightServiceImpl implements FreightService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FreightRepository freightRepository;

    @Override
    @CacheEvict(value="freightList",allEntries=true)
    public void addOrUpdate(FreightInfo freightInfo) {
        freightRepository.save(freightInfo);
    }

    @Override
    @Cacheable(value = "freightList", key="#freightInfo",unless="!(#result!=null)")
    public List<FreightInfo> findList(FreightInfo freightInfo){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("departure", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("destination", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<FreightInfo> ex = Example.of(freightInfo, matcher);

        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");

        List<FreightInfo> freightInfos = freightRepository.findAll(ex,sort);

        return freightInfos;
    }

    @CacheEvict(value="freightList",allEntries=true)
    public void delete(FreightInfo freightInfo){
        freightRepository.delete(freightInfo);
    }

    @CacheEvict(value="freightList",allEntries=true)
    @Scheduled(fixedDelay = 3*60*1000)
    public void clearCache(){
        logger.info("now clean freight info list cache");
    }
}
