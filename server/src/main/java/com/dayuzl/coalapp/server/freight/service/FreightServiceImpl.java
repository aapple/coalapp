package com.dayuzl.coalapp.server.freight.service;

import com.dayuzl.coalapp.server.framework.domain.PageParam;
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

    @Autowired
    private FreightRepository freightRepository;

    @Override
    @CacheEvict(value="freightCache",allEntries=true)
    public void save(FreightInfo freightInfo) {
        freightRepository.save(freightInfo);
    }

    @Override
    @Cacheable(value = "freightCache", key="#freightInfo+'List'",unless="!(#result!=null)")
    public List<FreightInfo> getList(FreightInfo freightInfo){

        Example<FreightInfo> ex = this.buildExample(freightInfo);

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");

        List<FreightInfo> freightInfos = freightRepository.findAll(ex,sort);

        return freightInfos;
    }

    @Override
    @Cacheable(value = "freightCache", key="#freightInfo+'Page'",unless="!(#result!=null)")
    public Page<FreightInfo> getPage(FreightInfo freightInfo){

        Example<FreightInfo> ex = this.buildExample(freightInfo);

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Integer pageNumber = freightInfo.getPageNumber()!=null ? freightInfo.getPageNumber() : 0;
        Integer pageSize = freightInfo.getPageSize()!=null? freightInfo.getPageSize() : 10;
        Pageable pageRequest = new PageRequest(pageNumber, pageSize, sort);

        Page<FreightInfo> freightInfos = freightRepository.findAll(ex,pageRequest);

        return freightInfos;
    }


    private Example<FreightInfo> buildExample(FreightInfo freightInfo){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("departure", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("destination", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        return Example.of(freightInfo, matcher);
    }


    @CacheEvict(value="freightCache",allEntries=true)
    public void delete(FreightInfo freightInfo){
        freightRepository.delete(freightInfo);
    }

    @CacheEvict(value="freightList",allEntries=true)
    @Scheduled(fixedDelay = 3*60*1000)
    public void clearCache(){
    }
}
