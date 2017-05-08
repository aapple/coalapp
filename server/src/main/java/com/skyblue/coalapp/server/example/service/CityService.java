package com.skyblue.coalapp.server.example.service;


import com.skyblue.coalapp.server.example.domain.City;

/**
 * Created by yaobin on 2017/4/28.
 */
public interface CityService {

    City findByName(String name);
    
}
