package com.skyblue.coalapp.server.timeline.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yao on 2017/5/21.
 */
@RestController
@RequestMapping("app/")
public class TimelineController {


    // timeline?type=refresh&id=' + params.id
    // timeline?type=infinite&id=' + params.id
    @RequestMapping("/index")
    String timeline(){
        return "";
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
