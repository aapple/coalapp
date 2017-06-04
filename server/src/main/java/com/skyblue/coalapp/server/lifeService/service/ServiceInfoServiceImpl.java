package com.skyblue.coalapp.server.lifeService.service;

import com.skyblue.coalapp.server.lifeService.Repository.CustomerEvaluateRepository;
import com.skyblue.coalapp.server.lifeService.Repository.LifeServiceInfoRepository;
import com.skyblue.coalapp.server.lifeService.domain.CustomerEvaluate;
import com.skyblue.coalapp.server.lifeService.domain.LifeServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInfoServiceImpl implements ServiceInfoService {

    @Autowired
    private LifeServiceInfoRepository lifeServiceInfoRepository;

    @Autowired
    private CustomerEvaluateRepository customerEvaluateRepository;

    @Override
    public void addOrUpdate(LifeServiceInfo lifeServiceInfo) {
        lifeServiceInfoRepository.save(lifeServiceInfo);
    }

    @Override
    public LifeServiceInfo findOne(LifeServiceInfo lifeServiceInfo) {

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<LifeServiceInfo> ex = Example.of(lifeServiceInfo, matcher);

        //查询
        LifeServiceInfo info = lifeServiceInfoRepository.findOne(ex);

        return info;
    }

    @Override
    public List<LifeServiceInfo> findList(LifeServiceInfo lifeServiceInfo) {

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<LifeServiceInfo> ex = Example.of(lifeServiceInfo, matcher);

        //查询
        List<LifeServiceInfo> info = lifeServiceInfoRepository.findAll(ex);

        return info;
    }

    @Override
    public void addEvaluate(CustomerEvaluate customerEvaluate) {
        customerEvaluateRepository.save(customerEvaluate);
    }

    @Override
    public List<CustomerEvaluate> getEvaluateList(LifeServiceInfo lifeServiceInfo) {

        CustomerEvaluate customerEvaluate = new CustomerEvaluate();
        customerEvaluate.setLifeServiceInfo(lifeServiceInfo);

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<CustomerEvaluate> ex = Example.of(customerEvaluate, matcher);

        //查询
        List<CustomerEvaluate> evaluates = customerEvaluateRepository.findAll(ex);

        return evaluates;
    }
}
