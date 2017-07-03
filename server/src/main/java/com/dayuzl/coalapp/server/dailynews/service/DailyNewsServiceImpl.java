package com.dayuzl.coalapp.server.dailynews.service;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import com.dayuzl.coalapp.server.dailynews.repository.DailyNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class DailyNewsServiceImpl implements DailyNewsService {

    @Autowired
    private DailyNewsRepository dailyNewsRepository;

    @Override
    @CacheEvict(value="dailyNewsList",allEntries=true)
    public void save(DailyNews dailyNews) {
        dailyNewsRepository.save(dailyNews);
    }

    @Override
    @Cacheable(value = "dailyNewsList", key="#pageNumber",unless="!(#result!=null)")
    public Page<DailyNews> findList(Integer pageNumber){

        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageRequest = new PageRequest(pageNumber, 10, sort);

        Page<DailyNews> dailyNewss = dailyNewsRepository.findAll(pageRequest);

        return dailyNewss;
    }
}
