package com.skyblue.coalapp.server.lifeService.controller;

import com.skyblue.coalapp.server.framework.util.ResponseUtils;
import com.skyblue.coalapp.server.lifeService.domain.CustomerEvaluate;
import com.skyblue.coalapp.server.lifeService.domain.LifeServiceInfo;
import com.skyblue.coalapp.server.lifeService.domain.LifeServiceProvider;
import com.skyblue.coalapp.server.lifeService.service.ServiceInfoService;
import com.skyblue.coalapp.server.lifeService.service.ServiceProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信息部 controller
 */

@RestController
@RequestMapping("/app/life")
public class LifeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private ServiceInfoService serviceInfoService;

    /*
    * 新增或更新 周边服务点
    * */
    @RequestMapping("/addOrUpdateLifeStore")
    public void addOrUpdateLifeStore(@RequestBody LifeServiceProvider lifeStore){

        logger.info("request param lifeServiceProvider:" + lifeStore);

        serviceProviderService.saveOrUpdate(lifeStore);
    }

    /*
    * 获取周边服务点列表
    * */
    @RequestMapping("/getLifeStoreList")
    public String getLifeStoreList(@RequestBody LifeServiceProvider lifeStore){

        logger.info("request param lifeServiceProvider:" + lifeStore);

        List<LifeServiceProvider> lifeStoreList = serviceProviderService.findList(lifeStore);

        return ResponseUtils.toJSONString(lifeStoreList);
    }

    /*
    * 新增服务信息列表
    * */
    @RequestMapping("/addOrUpdateLifeInfo")
    public void addOrUpdateLifeInfo(@RequestBody LifeServiceInfo info){

        serviceInfoService.addOrUpdate(info);
    }

    /*
     * 获取产品信息列表
     * */
    @RequestMapping("/getLifeInfoList")
    public String getLifeInfoList(@RequestBody LifeServiceInfo info){

        List<LifeServiceInfo> lifeInfoList = serviceInfoService.findList(info);

        return ResponseUtils.toJSONString(lifeInfoList);
    }

     /*
     * 新增用户评价
     * */
     @RequestMapping("/addCustomerEvaluate")
     public void addCustomerEvaluate(@RequestBody CustomerEvaluate evaluate){
          serviceInfoService.addEvaluate(evaluate);
     }

    /*
     * 获取产品信息列表
     * */
    @RequestMapping("/getCustEvaluateList")
    public String getEvaluateList(@RequestBody LifeServiceInfo info){

        List<CustomerEvaluate> evaluateList = serviceInfoService.getEvaluateList(info);

        return ResponseUtils.toJSONString(evaluateList);
    }
}
