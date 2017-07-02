package com.dayuzl.coalapp.server.example.controller;

import com.dayuzl.coalapp.server.example.service.CityService;
import com.dayuzl.coalapp.server.framework.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaobin on 2017/4/28.
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseMessage getCity(@PathVariable String name){

        return ResponseMessage.ok(cityService.findByName(name));
    }
}
