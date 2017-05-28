package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.product.repository.FactoryRepository;
import com.skyblue.coalapp.server.product.repository.ProductTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    @Override
    public void save(Factory industry) {
        factoryRepository.save(industry);
    }

    @Override
    public void deleteById(Integer id) {

        Factory industry = new Factory();
        industry.setId(id);

        factoryRepository.delete(industry);
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
