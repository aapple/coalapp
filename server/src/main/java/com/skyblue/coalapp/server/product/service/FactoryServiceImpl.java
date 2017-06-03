package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.product.repository.FactoryRepository;
import com.skyblue.coalapp.server.product.repository.ProductTypeRepository;
import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

;

/**
 * Created by 张杨 on 2017/5/19.
 */
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

            User user = userService.findById(factory.getOnwer().getId());
            if(factory.getFactoryType() == 1) {
                user.setIsCoalManager(1);
            } else {
                user.setIsCokeManager(1);
            }
            userService.updateUserInfo(user);
        }

        if(factory.getSaler() != null && factory.getSaler().getId() != null) {

            User user = userService.findById(factory.getSaler().getId());
            user.setIsCoalSaler(1);
            userService.updateUserInfo(user);
        }
    }

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

}
