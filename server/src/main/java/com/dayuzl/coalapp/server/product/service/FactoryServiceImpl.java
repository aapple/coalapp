package com.dayuzl.coalapp.server.product.service;

import com.dayuzl.coalapp.server.product.repository.ProductTypeRepository;
import com.dayuzl.coalapp.server.user.domain.User;
import com.dayuzl.coalapp.server.product.domain.Factory;
import com.dayuzl.coalapp.server.product.domain.ProductType;
import com.dayuzl.coalapp.server.product.repository.FactoryRepository;
import com.dayuzl.coalapp.server.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private UserService userService;

    @Override
    public void deleteById(Factory factory) {

        factoryRepository.delete(factory);
    }

    @Override
    @CacheEvict(value="factoryList",allEntries=true)
    public void saveOrUpdateFactory(Factory factory){

        if(factory.getOnwer() != null && factory.getOnwer().getId() == null){
            factory.setOnwer(null);
        }
        if(factory.getSaler() != null && factory.getSaler().getId() == null){
            factory.setSaler(null);
        }
        factory.setCreatedTime(new Date());
        factoryRepository.save(factory);

        // 用户权限更新
        if(factory.getOnwer() != null && factory.getOnwer().getId() != null) {

            User user = userService.findUser(factory.getOnwer());
            if(factory.getFactoryType() == 1) {
                user.setIsCoalManager(1);
            } else {
                user.setIsCokeManager(1);
            }
            userService.updateUserInfo(user);
        }

        if(factory.getSaler() != null && factory.getSaler().getId() != null) {

            User user = userService.findUser(factory.getSaler());
            user.setIsCoalSaler(1);
            userService.updateUserInfo(user);
        }
    }

    @Override
    @Cacheable(value = "factoryList", key="#factory.toString()",unless="!(#result.size()>0)")
    public List<Factory> getFactoryList(Factory factory){

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<Factory> ex = Example.of(factory, matcher);

        //查询
        List<Factory> factoryList = factoryRepository.findAll(ex);

        return factoryList;
    }

    @Override
    @Cacheable(value = "productTypeList", key="#productType.toString()",unless="!(#result.size()>0)")
    public List<ProductType> getProductTypeList(ProductType productType){

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<ProductType> ex = Example.of(productType, matcher);

        //查询
        List<ProductType> productTypeList = productTypeRepository.findAll(ex);

        return productTypeList;
    }

    @CacheEvict(value="factoryList",allEntries=true)
    @Scheduled(fixedDelay = 5*60*1000)
    public void clearCache(){
        logger.info("it's time to clean factories cache");
    }
}
