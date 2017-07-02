package com.dayuzl.coalapp.spider.chapter;

import com.dayuzl.coalapp.spider.service.SpiderService;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Service("chapterPipeline")
public class ChapterPipeline implements Pipeline<Chapter>{

    @Autowired
    private SpiderService spiderService;

    @Override
    public void process(Chapter chapter) {
        String body = chapter.getBody();
        spiderService.saveChapterText(body, chapter.getRequest().getUrl());
    }
}
