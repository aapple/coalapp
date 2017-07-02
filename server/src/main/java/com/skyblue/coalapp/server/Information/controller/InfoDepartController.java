package com.skyblue.coalapp.server.Information.controller;

import com.skyblue.coalapp.server.Information.domain.InfoDepartment;
import com.skyblue.coalapp.server.Information.domain.LogisticsInfo;
import com.skyblue.coalapp.server.Information.service.InfoDepartService;
import com.skyblue.coalapp.server.Information.service.LogisticsInfoService;
import com.skyblue.coalapp.server.framework.util.ResponseUtils;
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
@RequestMapping("/app/infoDepart")
public class InfoDepartController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InfoDepartService infoDepartService;

    @Autowired
    private LogisticsInfoService logisticsInfoService;

    @RequestMapping("/addOrUpdateInfoDepart")
    public void addOrUpdate(@RequestBody InfoDepartment infoDepart){

        logger.info("request param InfoDepartment:" + infoDepart);

        infoDepartService.addInfoDepartment(infoDepart);
    }

    @RequestMapping("/getInfoDepartment")
    public String findInfoDepartment(@RequestBody InfoDepartment infoDepart){
        logger.info("request param InfoDepartment:" + infoDepart);

        InfoDepartment infoDepartment = infoDepartService.findOne(infoDepart);

        return ResponseUtils.toJSONString(infoDepartment);
    }

    @RequestMapping("/getInfoDepartmentList")
    public String findInfoDepartmentList(@RequestBody InfoDepartment infoDepart){
        logger.info("request param InfoDepartment:" + infoDepart);

        List<InfoDepartment> infoDepartmetList = infoDepartService.findAll(infoDepart);

        return ResponseUtils.toJSONString(infoDepartmetList);
    }

    @RequestMapping("/deleteInfoDepartment")
    public void deleteInfoDepartment(@RequestBody InfoDepartment infoDepart){

        logger.info("request param InfoDepartment:" + infoDepart);

        infoDepartService.deleteById(infoDepart);
    }

    @RequestMapping("/addOrUpdateLogisticsInfo")
    public void addOrUpdateLogisticsInfo(@RequestBody LogisticsInfo logisticsInfo){
        logger.info("request param LogisticsInfo: " + logisticsInfo);

       logisticsInfoService.saveOrUpdate(logisticsInfo);
    }

    @RequestMapping("/getLogisticsList")
    public String getLogisticsList(@RequestBody LogisticsInfo logisticsInfo){
        logger.info("request param LogisticsInfo: " + logisticsInfo);

        List<LogisticsInfo> resultList = logisticsInfoService.findList(logisticsInfo);

        return ResponseUtils.toJSONString(resultList);
    }

    @RequestMapping("/getLogisticsDetail")
    public String getLogisticsDetail(@RequestBody LogisticsInfo logisticsInfo){
        logger.info("request param LogisticsInfo: " + logisticsInfo);
        LogisticsInfo result = logisticsInfoService.findOne(logisticsInfo);

        return ResponseUtils.toJSONString(result);
    }

    @RequestMapping("/deleteLogistics")
    public void deleteLogistics(@RequestBody LogisticsInfo logisticsInfo){
        logger.info("request param LogisticsInfo: " + logisticsInfo);
        logisticsInfoService.deleteById(logisticsInfo);
    }
}
