package com.dayuzl.coalapp.spider.task;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TrafficTask extends SpringGeccoEngine {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 */1 * * * ? ")
    public void loadTrafficInfo() {

        logger.info("traffic task开始");

        GeccoEngine.create()
                //工程的包路径
                .classpath("com.dayuzl.coalapp.spider")
                .debug(false)
                .pipelineFactory(springPipelineFactory)
                //开始抓取的页面地址
                .start("http://www.meilaoban.com/index.php/Mobile/RoadCondition/index.html?rid=1")
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

        logger.info("traffic task结束");
    }

    @Override
    public void init() {

    }
}
