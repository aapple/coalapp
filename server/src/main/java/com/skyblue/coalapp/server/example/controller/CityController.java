package com.skyblue.coalapp.server.example.controller;

import com.skyblue.coalapp.server.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yaobin on 2017/4/28.
 */
@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseMessage getCity(@PathVariable String name){

        return ResponseMessage.ok(cityService.findByName(name));
    }
}
