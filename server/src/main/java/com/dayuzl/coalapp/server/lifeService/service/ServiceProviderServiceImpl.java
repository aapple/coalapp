package com.dayuzl.coalapp.server.lifeService.service;

import com.dayuzl.coalapp.server.lifeService.Repository.LifeServiceProviderRepository;
import com.dayuzl.coalapp.server.lifeService.domain.LifeServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value="lifeServiceProviderList",allEntries=true)
    public void saveOrUpdate(LifeServiceProvider lifeServiceProvider) {

        lifeServiceProvider.setCreatedTime(new Date());

        lifeServiceProviderRepository.save(lifeServiceProvider);
    }

    @Override
    @Cacheable(value = "lifeServiceProvider", key="#lifeServiceProvider.toString()",unless="#result!=null")
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
    @Cacheable(value = "lifeServiceProviderList", key="#lifeServiceProvider.toString()",unless="!(#result.size()>0)")
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
