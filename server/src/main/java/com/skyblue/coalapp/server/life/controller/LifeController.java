package com.skyblue.coalapp.server.life.controller;

import com.skyblue.coalapp.server.framework.ResponseUtils;
import com.skyblue.coalapp.server.life.domain.LifeStore;
import com.skyblue.coalapp.server.life.service.LifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 信息部 controller
 */

@RestController
@RequestMapping("/app/life")
public class LifeController {

    @Autowired
    private LifeService lifeService;

    @RequestMapping("/addOrUpdateLifeStore")
    public void addOrUpdateLifeStore(@RequestBody LifeStore lifeStore){

        lifeStore.setCreatedTime(new Date());
        lifeService.addInfoDepartment(lifeStore);
    }

    @RequestMapping("/getLifeStoreList")
    public String getLifeStoreList(@RequestBody LifeStore lifeStore){

        List<LifeStore> lifeStoreList = lifeService.findAll(lifeStore);

        return ResponseUtils.toJSONString(lifeStoreList);
    }

    @RequestMapping("/deleteLifeStore")
    public void deleteLifeStore(@RequestBody LifeStore lifeStore){

        lifeService.deleteById(lifeStore);
    }

}
