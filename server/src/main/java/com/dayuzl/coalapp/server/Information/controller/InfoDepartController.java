package com.dayuzl.coalapp.server.Information.controller;

import com.dayuzl.coalapp.server.Information.domain.LogisticsInfo;
import com.dayuzl.coalapp.server.Information.service.LogisticsInfoService;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.Information.domain.InfoDepartment;
import com.dayuzl.coalapp.server.Information.service.InfoDepartService;
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

    @RequestMapping("/deleteInfoDepartment")
    public void deleteInfoDepartment(@RequestBody InfoDepartment infoDepart){

        infoDepartService.deleteById(infoDepart);
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

    @RequestMapping("/deleteLogistics")
    public void deleteLogistics(@RequestBody LogisticsInfo logisticsInfo){
        logisticsInfoService.deleteById(logisticsInfo);
    }
}
