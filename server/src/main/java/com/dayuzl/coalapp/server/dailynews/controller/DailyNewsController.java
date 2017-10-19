package com.dayuzl.coalapp.server.dailynews.controller;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import com.dayuzl.coalapp.server.dailynews.service.DailyNewsService;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 信息部 controller
 */

@RestController
@RequestMapping("/app/news")
public class DailyNewsController {

    @Autowired
    private DailyNewsService dailyNewsService;

    @RequestMapping("/addDailyNews")
    public void saveDailyNews(@RequestBody DailyNews dailyNews){


        dailyNewsService.save(dailyNews);
    }

    @RequestMapping("/getDailyNewsList")
    public String getDailyNewsList(Integer pageNumber){

        if(pageNumber == null){
            pageNumber = new Integer(0);
        }

        Page<DailyNews> result = dailyNewsService.findPage(pageNumber);

        return ResponseUtils.toJSONString(result);
    }
}
