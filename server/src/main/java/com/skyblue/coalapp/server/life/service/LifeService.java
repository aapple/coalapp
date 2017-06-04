package com.skyblue.coalapp.server.life.service;

import com.skyblue.coalapp.server.life.domain.LifeStore;

import java.util.List;

/**
 * 信息部 service
 */

public interface LifeService {

    void addInfoDepartment(LifeStore lifeStore);

    List<LifeStore> findAll(LifeStore lifeStore);

    void deleteById(LifeStore lifeStore);
}