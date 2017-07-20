package com.dayuzl.coalapp.server.dailynews.service;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import com.dayuzl.coalapp.server.dailynews.repository.DailyNewsRepository;
import com.dayuzl.coalapp.server.framework.domain.PageParam;
import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DailyNewsServiceImpl implements DailyNewsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DailyNewsRepository dailyNewsRepository;

    @Override
    public void save(DailyNews dailyNews) {
        dailyNewsRepository.save(dailyNews);
    }

    @Override
    public Page<DailyNews> findPage(PageParam page){

        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");

        Integer pageNumber = page.getPageNumber() != null ? page.getPageNumber() : 0;
        Integer pageSize = page.getPageSize() != null ?  page.getPageSize() : 10;

        Pageable pageRequest = new PageRequest(pageNumber, pageSize, sort);

        Page<DailyNews> dailyNewss = dailyNewsRepository.findAll(pageRequest);

        return dailyNewss;
    }

    @Scheduled(fixedDelay = 3*60*1000)
    public void clearCache(){
        logger.info("now clean daily news page cache");
    }
}
