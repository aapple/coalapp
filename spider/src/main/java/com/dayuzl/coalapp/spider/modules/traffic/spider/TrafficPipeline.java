package com.dayuzl.coalapp.spider.modules.traffic.spider;

import com.dayuzl.coalapp.spider.modules.traffic.domain.TrafficInfo;
import com.dayuzl.coalapp.spider.modules.traffic.repository.TrafficInfoRepository;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Service("trafficPipeline")
public class TrafficPipeline implements Pipeline<Traffic>{

    @Autowired
    private TrafficInfoRepository trafficInfoRepository;

    @Override
    public void process(Traffic traffic) {

        HttpRequest currRequest = traffic.getRequest();
        List<TrafficNode> trafficNodes = traffic.getQianNode();
        trafficNodes.addAll(traffic.getShenNode());

        // 清空原数据
        if(currRequest.getUrl().endsWith("rid=1")){
            trafficInfoRepository.deleteBySpiderFlag(1);
        }

        for(TrafficNode trafficNode : trafficNodes) {
            TrafficInfo trafficInfo = new TrafficInfo();
            trafficInfo.setSpiderFlag(1);
            trafficInfo.setDeparture(trafficNode.getData().get(0));
            trafficInfo.setState(trafficNode.getData().get(1));
            trafficInfo.setTrafficTime(trafficNode.getData().get(2));

            if(currRequest.getUrl().endsWith("rid=1")){
                trafficInfo.setArea("陕西");
            } else if(currRequest.getUrl().endsWith("rid=2")){
                trafficInfo.setArea("山西");
            } else if(currRequest.getUrl().endsWith("rid=3")){
                trafficInfo.setArea("河北");
            }
            trafficInfoRepository.save(trafficInfo);
        }

        if(currRequest.getUrl().endsWith("rid=1")){
            SchedulerContext.into(currRequest.subRequest("http://www.meilaoban.com/index.php/Mobile/RoadCondition/index.html?rid=2"));
        } else if(currRequest.getUrl().endsWith("rid=2")){
            SchedulerContext.into(currRequest.subRequest("http://www.meilaoban.com/index.php/Mobile/RoadCondition/index.html?rid=3"));
        }

    }
}
