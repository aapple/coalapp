package com.dayuzl.coalapp.server.lifeService.service;


import com.dayuzl.coalapp.server.lifeService.domain.CustomerEvaluate;
import com.dayuzl.coalapp.server.lifeService.domain.LifeServiceInfo;

import java.util.List;

public interface ServiceInfoService {

    //新增或更新
    void addOrUpdate(LifeServiceInfo lifeServiceInfo);

    LifeServiceInfo findOne(LifeServiceInfo lifeServiceInfo);

    //查找列表
    List<LifeServiceInfo> findList(LifeServiceInfo lifeServiceInfo);

    //新增客户评价
    void addEvaluate(CustomerEvaluate customerEvaluate);

    //获取客户评价列表
    List<CustomerEvaluate> getEvaluateList(LifeServiceInfo lifeServiceInfo);
}
