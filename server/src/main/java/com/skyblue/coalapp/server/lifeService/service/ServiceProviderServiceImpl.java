package com.skyblue.coalapp.server.lifeService.service;

import com.skyblue.coalapp.server.lifeService.Repository.LifeServiceProviderRepository;
import com.skyblue.coalapp.server.lifeService.domain.LifeServiceProvider;
import com.skyblue.coalapp.server.product.domain.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService{

    @Autowired
    private LifeServiceProviderRepository lifeServiceProviderRepository;

    @Override
    public void saveOrUpdate(LifeServiceProvider lifeServiceProvider) {

        lifeServiceProvider.setCreatedTime(new Date());

        lifeServiceProviderRepository.save(lifeServiceProvider);
    }

    @Override
    public LifeServiceProvider findOne(LifeServiceProvider lifeServiceProvider){
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<LifeServiceProvider> ex = Example.of(lifeServiceProvider, matcher);

        //查询
        LifeServiceProvider provider = lifeServiceProviderRepository.findOne(ex);

        return provider;
    }

    @Override
    public List<LifeServiceProvider> findList(LifeServiceProvider lifeServiceProvider) {

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<LifeServiceProvider> ex = Example.of(lifeServiceProvider, matcher);

        //查询
        List<LifeServiceProvider> providerList = lifeServiceProviderRepository.findAll(ex);

        return providerList;
    }
}
