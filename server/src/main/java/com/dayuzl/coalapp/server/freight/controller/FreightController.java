package com.dayuzl.coalapp.server.freight.controller;

import com.dayuzl.coalapp.server.Information.domain.InfoDepartment;
import com.dayuzl.coalapp.server.Information.domain.LogisticsInfo;
import com.dayuzl.coalapp.server.Information.service.InfoDepartService;
import com.dayuzl.coalapp.server.Information.service.LogisticsInfoService;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.freight.domain.FreightInfo;
import com.dayuzl.coalapp.server.freight.service.FreightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 运费维护 controller
 */

@RestController
@RequestMapping("/app/freight")
public class FreightController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FreightService freightService;

    @RequestMapping("/addOrUpdate")
    public void addOrUpdate(@RequestBody FreightInfo freightInfo){

        logger.info("request param FreightInfo:" + freightInfo);

        freightService.addOrUpdate(freightInfo);
    }

    @RequestMapping("/getFreightList")
    public String getFreightList(@RequestBody FreightInfo freightInfo){

        logger.info("request param FreightInfo: " + freightInfo);

        List<FreightInfo> resultList = freightService.findList(freightInfo);

        return ResponseUtils.toJSONString(resultList);
    }

    @RequestMapping("/delete")
    public void deleteInfoDepartment(@RequestBody FreightInfo freightInfo){

        logger.info("request param FreightInfo:" + freightInfo);

        freightService.delete(freightInfo);
    }
}
