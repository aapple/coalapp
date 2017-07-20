package com.dayuzl.coalapp.server.freight.controller;

import com.dayuzl.coalapp.server.framework.domain.PageParam;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.freight.domain.FreightInfo;
import com.dayuzl.coalapp.server.freight.service.FreightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void saveFreight(@RequestBody FreightInfo freightInfo){

        logger.info("saveFreight: request param FreightInfo -->" + freightInfo);

        freightService.save(freightInfo);
    }

    @RequestMapping("/getFreightList")
    public String getFreights(@RequestBody FreightInfo freightInfoVO){

        logger.info("getFreightList : request param FreightInfo --> " + freightInfoVO);

        if(freightInfoVO.getPageNumber() != null && freightInfoVO.getPageSize() != null){
            return ResponseUtils.toJSONString(freightService.getPage(freightInfoVO));
        }else{
            return ResponseUtils.toJSONString(freightService.getList(freightInfoVO));
        }
    }

    @RequestMapping("/deleteFreight")
    public void deleteFreight(@RequestBody FreightInfo freightInfo){

        logger.info("deleteFreight: request param FreightInfo -->" + freightInfo);

        freightService.delete(freightInfo);
    }
}
