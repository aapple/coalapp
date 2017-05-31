package com.skyblue.coalapp.server.product.controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.framework.RequestUtils;
import com.skyblue.coalapp.server.framework.ResponseUtils;
import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductPrice;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.product.service.FactoryService;
import com.skyblue.coalapp.server.product.service.ProductPriceService;
import com.skyblue.coalapp.server.user.domain.User;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 张杨 on 2017/5/19.
 */
@RestController
@RequestMapping("/app/product")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private ProductPriceService productPriceService;

    /*
    *  查询产品价格列表
    *
    *  1. 默认只有facotryType这样查询出来的是当前种类产品的所有列表
    *  2. 也可以有其他的过滤条件
    * */
    @RequestMapping("/getProductPriceList")
    public String getProductPriceList(@RequestBody ProductPrice productPrice){

        List<ProductPrice> productList = productPriceService.getProductPriceList(productPrice);

        return ResponseUtils.toJSONString(productList);
    }

    /*
    *  查询用户的工厂和产品列表
    * */
    @RequestMapping("/getFactoryList")
    String getFactoryList(@RequestBody Factory factory){

        User userInfo = RequestUtils.getUserInfo();
        factory.setUser(userInfo);
        List<Factory> factoryList = factoryService.getFactoryList(factory);

        ProductType productType = new ProductType();
        productType.setFactoryType(factory.getFactoryType());
        List<ProductType> productTypeList = factoryService.getProductTypeList(productType);

        Map<String, Object> result = new HashedMap();
        result.put("factoryList", factoryList);
        result.put("productTypeList", productTypeList);
        return ResponseUtils.toJSONString(result);
    }

    /*
    *  查询产品列表
    * */
    @RequestMapping("/getProductTypeList")
    String getProductTypeList(@RequestBody Factory factory){

        ProductType productType = new ProductType();
        productType.setFactoryType(factory.getFactoryType());

        List<ProductType> productTypeList = factoryService.getProductTypeList(productType);

        return ResponseUtils.toJSONString(productTypeList);
    }

    /*
   *  查询产品列表模板
   * */
    @RequestMapping("/getProductPriceTempList")
    String getProductPriceTempList(@RequestBody Factory factory){

        ProductType productType = new ProductType();
        productType.setFactoryType(factory.getFactoryType());

        List<ProductPrice> productList = productPriceService.getProdcutPriceTemplateList(productType);

        return ResponseUtils.toJSONString(productList);
    }

    /*
    *  保存或更新价格列表
    * */
    @RequestMapping("/saveOrUpdateProductPrice")
    public void saveOrUpdateProductPrice(@RequestBody ProductPrice productPrice){

        productPriceService.saveOrUpdateProductPrice(productPrice);
    }

}
