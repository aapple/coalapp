package com.skyblue.coalapp.server.example.service;

import com.skyblue.coalapp.server.example.domain.City;
import com.skyblue.coalapp.server.example.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by yaobin on 2017/4/28.
 */
@Service
public class CityServiceImpl implements CityService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City findByName(String name) {
        logger.info("success");
        City city = cityRepository.findByName(name);
//        Assert.notNull(city, "can't find city with name " + name);
        return city;
    }
}
