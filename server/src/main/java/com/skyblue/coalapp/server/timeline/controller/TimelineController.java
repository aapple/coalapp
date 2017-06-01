package com.skyblue.coalapp.server.timeline.controller;

import com.skyblue.coalapp.server.framework.ResponseUtils;
import com.skyblue.coalapp.server.product.service.FactoryService;
import com.skyblue.coalapp.server.timeline.service.TimelinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yao on 2017/5/21.
 */
@RestController
@RequestMapping("app/")
public class TimelineController {

    @Autowired
    private TimelinesService timelinesService;

    // timeline?type=refresh&id=' + params.id
    // timeline?type=infinite&id=' + params.id
    @RequestMapping("/timeline")
    String timeline(){

        timelinesService.xxx();
        return ResponseUtils.toJSONString(productList);
    }

    @RequestMapping("/set-like")
    String setLike(){

        return "";
    }

    @RequestMapping("/store")
    String store(){

        return "";
    }

    @RequestMapping("/update")
    String update(){

        return "";
    }

    @RequestMapping("/destroy")
    String destroy(){

        return "";
    }

    @RequestMapping("/store-comment")
    String storeComment(){

        return "";
    }

}
