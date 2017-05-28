package com.skyblue.coalapp.server.Information.controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.Information.domain.InfoDepartment;
import com.skyblue.coalapp.server.Information.service.InfoDepartService;
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

    @RequestMapping("/addOrUpdate")
    public void addOrUpdate(@RequestBody InfoDepartment infoDepart){
        infoDepartService.addInfoDepartment(infoDepart);
    }

    @RequestMapping("/getInfoDepartment")
    public String findInfoDepartment(@RequestBody InfoDepartment infoDepart){
        InfoDepartment infoDepartment = infoDepartService.findOne(infoDepart);

        return JSON.toJSONString(infoDepartment);
    }

    @RequestMapping("/getInfoDepartmentList")
    public String findInfoDepartmentList(@RequestBody InfoDepartment infoDepart){

        List<InfoDepartment> infoDepartmetList = infoDepartService.findAll(infoDepart);

        return JSON.toJSONString(infoDepartmetList);
    }
}
