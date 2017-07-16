package com.dayuzl.coalapp.spider.example.book;

import com.dayuzl.coalapp.spider.example.CommonDao;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Service("bookPipeline")
public class BookPipeline implements Pipeline<CDBook>{

    @Autowired
    private CommonDao commonDao;

    @Override
    public void process(CDBook book) {
        List<ChapterNode> chapterNodes = book.getChapterNodes();
        for(ChapterNode chapterNode : chapterNodes) {
            String name = chapterNode.getName();
            String href = chapterNode.getHref();
            HttpRequest currRequest = book.getRequest();
            commonDao.saveChapter(name, href, currRequest.getUrl());
            SchedulerContext.into(currRequest.subRequest(href));
        }
    }
}
