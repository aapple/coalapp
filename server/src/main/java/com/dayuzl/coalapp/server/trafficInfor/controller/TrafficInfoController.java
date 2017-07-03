package com.dayuzl.coalapp.server.trafficInfor.controller;

import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.trafficInfor.domain.TrafficInfo;
import com.dayuzl.coalapp.server.trafficInfor.service.TrafficInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信息部 controller
 */

@RestController
@RequestMapping("/app/traffic")
public class TrafficInfoController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrafficInfoService trafficInfoService;

    @RequestMapping("/addTrafficInfo")
    public void addTrafficInfo(@RequestBody TrafficInfo trafficInfo){
        logger.info("request param TrafficInfo: " + trafficInfo);

        trafficInfoService.saveOrUpdate(trafficInfo);
    }

    @RequestMapping("/getTrafficInfoList")
    public String getTrafficInfoList(Integer pageNumber){

        logger.info("request TrafficInfo");

        if(pageNumber == null){
            pageNumber = new Integer(0);
        }

        Page<TrafficInfo> resultList = trafficInfoService.findList(pageNumber);

        return ResponseUtils.toJSONString(resultList);
    }
}
