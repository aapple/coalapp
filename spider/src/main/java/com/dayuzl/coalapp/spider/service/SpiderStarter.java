package com.dayuzl.coalapp.spider.service;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.springframework.stereotype.Service;

/**
 * Created by bin.yao on 2017/4/21.
 */
@Service
public class SpiderStarter extends SpringGeccoEngine {

    @Override
    public void init() {
        GeccoEngine.create()
                //工程的包路径
                .classpath("com.young.wuxia.downloadnovels")
                .debug(false)
                .pipelineFactory(springPipelineFactory)
                //开始抓取的页面地址
//                .start("http://www.wuxiaworld.com")
                .start("http://www.wuxiaworld.com/cdindex-html/book-12-chapter-19-2/")
                //开启几个爬虫线程
                .thread(10)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(1)
                //循环抓取
                .loop(false)
                //使用pc端userAgent
                .mobile(false)
                //非阻塞方式运行
                .start();
    }
}
