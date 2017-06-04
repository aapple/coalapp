package com.skyblue.coalapp.server.lifeService.service;


import com.skyblue.coalapp.server.lifeService.domain.LifeServiceProvider;

import java.util.List;

public interface ServiceProviderService {

    //注册成为商户或更新商户信息
    void saveOrUpdate(LifeServiceProvider lifeServiceProvider);

    //查找某个商户
    LifeServiceProvider findOne(LifeServiceProvider lifeServiceProvider);

    //查询商户列表
    List<LifeServiceProvider> findList(LifeServiceProvider lifeServiceProvider);
}
