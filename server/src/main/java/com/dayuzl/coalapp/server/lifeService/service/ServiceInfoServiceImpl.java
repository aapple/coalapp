package com.dayuzl.coalapp.server.lifeService.service;

import com.dayuzl.coalapp.server.lifeService.Repository.CustomerEvaluateRepository;
import com.dayuzl.coalapp.server.lifeService.Repository.LifeServiceInfoRepository;
import com.dayuzl.coalapp.server.lifeService.domain.CustomerEvaluate;
import com.dayuzl.coalapp.server.lifeService.domain.LifeServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInfoServiceImpl implements ServiceInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LifeServiceInfoRepository lifeServiceInfoRepository;

    @Autowired
    private CustomerEvaluateRepository customerEvaluateRepository;

    @Override
    @CacheEvict(value="lifeServiceInfoList",allEntries=true)
    public void addOrUpdate(LifeServiceInfo lifeServiceInfo) {
        lifeServiceInfoRepository.save(lifeServiceInfo);
    }

    @Override
    @Cacheable(value = "lifeServiceInfo", key="#lifeServiceInfo.toString()",unless="#result!=null")
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
    @Cacheable(value = "lifeServiceInfoList", key="#lifeServiceInfo.toString()",unless="!(#result.size()>0)")
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

    @CacheEvict(value="lifeServiceInfoList",allEntries=true)
    @Scheduled(fixedDelay = 3*60*1000)
    public void clearCache(){
        logger.info("now clean life service info cache");
    }

    @Override
    @CacheEvict(value="customerEvaluateList",allEntries=true)
    public void addEvaluate(CustomerEvaluate customerEvaluate) {
        customerEvaluateRepository.save(customerEvaluate);
    }

    @Override
    @Cacheable(value = "customerEvaluateList", key="#lifeServiceInfo.toString()",unless="!(#result.size()>0)")
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
