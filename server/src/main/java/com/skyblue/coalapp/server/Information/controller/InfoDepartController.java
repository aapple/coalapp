package com.skyblue.coalapp.server.Information.controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.Information.domain.InfoDepartment;
import com.skyblue.coalapp.server.Information.domain.LogisticsInfo;
import com.skyblue.coalapp.server.Information.service.InfoDepartService;
import com.skyblue.coalapp.server.Information.service.LogisticsInfoService;
import com.skyblue.coalapp.server.framework.ResponseUtils;
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

    @Autowired
    private InfoDepartService infoDepartService;

    @Autowired
    private LogisticsInfoService logisticsInfoService;

    @RequestMapping("/addOrUpdateInfoDepart")
    public void addOrUpdate(@RequestBody InfoDepartment infoDepart){

        infoDepartService.addInfoDepartment(infoDepart);
    }

    @RequestMapping("/getInfoDepartment")
    public String findInfoDepartment(@RequestBody InfoDepartment infoDepart){
        InfoDepartment infoDepartment = infoDepartService.findOne(infoDepart);

        return ResponseUtils.toJSONString(infoDepartment);
    }

    @RequestMapping("/getInfoDepartmentList")
    public String findInfoDepartmentList(@RequestBody InfoDepartment infoDepart){

        List<InfoDepartment> infoDepartmetList = infoDepartService.findAll(infoDepart);

        return ResponseUtils.toJSONString(infoDepartmetList);
    }

    @RequestMapping("/addOrUpdateLogisticsInfo")
    public void addOrUpdateLogisticsInfo(@RequestBody LogisticsInfo logisticsInfo){

       logisticsInfoService.saveOrUpdate(logisticsInfo);
    }

    @RequestMapping("/getLogisticsList")
    public String getLogisticsList(@RequestBody LogisticsInfo logisticsInfo){

        List<LogisticsInfo> resultList = logisticsInfoService.findList(logisticsInfo);

        return ResponseUtils.toJSONString(resultList);
    }

    @RequestMapping("/getLogisticsDetail")
    public String getLogisticsDetail(@RequestBody LogisticsInfo logisticsInfo){

        LogisticsInfo result = logisticsInfoService.findOne(logisticsInfo);

        return ResponseUtils.toJSONString(result);
    }
}
