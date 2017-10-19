package com.dayuzl.coalapp.server.trafficinfo.controller;

import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.trafficinfo.domain.TrafficInfo;
import com.dayuzl.coalapp.server.trafficinfo.service.TrafficInfoService;
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
@RequestMapping("/app/traffic")
public class TrafficInfoController {

    @Autowired
    private TrafficInfoService trafficInfoService;

    @RequestMapping("/addTrafficInfo")
    public void addTrafficInfo(@RequestBody TrafficInfo trafficInfo){

        trafficInfoService.save(trafficInfo);
    }

    @RequestMapping("/getTrafficInfoList")
    public String getTrafficInfoList(@RequestBody TrafficInfo trafficVO){

        Page<TrafficInfo> resultPage = trafficInfoService.findPage(trafficVO);

        return ResponseUtils.toJSONString(resultPage);
    }
}
