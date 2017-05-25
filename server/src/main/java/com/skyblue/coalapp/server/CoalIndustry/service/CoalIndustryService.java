package com.skyblue.coalapp.server.CoalIndustry.service;

import com.skyblue.coalapp.server.CoalIndustry.domain.Factory;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface CoalIndustryService {

    public List<Factory> findAllByName(String name);

    public Factory findByCode(String name);

    public void save(Factory industry);

    public void deleteByCode(String code);

    public List<Factory> getFactoryProductsList();
}
