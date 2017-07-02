package com.dayuzl.coalapp.server.dailynews.service;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import org.springframework.data.domain.Page;

public interface DailyNewsService {

    void save(DailyNews dailyNews);

    Page<DailyNews> findList(Integer pageNumber);
}
