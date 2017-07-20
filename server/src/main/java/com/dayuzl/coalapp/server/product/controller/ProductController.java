package com.dayuzl.coalapp.server.product.controller;

import com.dayuzl.coalapp.server.framework.domain.PageParam;
import com.dayuzl.coalapp.server.framework.util.RequestUtils;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.product.domain.Factory;
import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import com.dayuzl.coalapp.server.product.domain.ProductType;
import com.dayuzl.coalapp.server.product.service.FactoryService;
import com.dayuzl.coalapp.server.product.service.ProductPriceService;
import com.dayuzl.coalapp.server.user.domain.User;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/product")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private ProductPriceService productPriceService;


    /*
     *  保存或更新价格列表
     * */
    @RequestMapping("/saveOrUpdateProductPrice")
    public void saveProductPrice(@RequestBody ProductPrice productPrice){

        logger.info("saveProductPrice : request param ProductPrice --> "+productPrice);

        productPriceService.save(productPrice);
    }

    /*
     *  查询产品价格列表
     *
     *  1. 默认只有facotryType这样查询出来的是当前种类产品的所有列表
     *  2. 也可以有其他的过滤条件
     * */
    @RequestMapping("/getProductPriceList")
    public String getProductPrices(@RequestBody ProductPrice  productPriceVO) {

        logger.info("getProductPrices : request param productPrice --> " + productPriceVO);

        if (productPriceVO.getPageNumber() != null && productPriceVO.getPageSize() != null) {
            return ResponseUtils.toJSONString(productPriceService.getPage(productPriceVO));
        } else {
            return ResponseUtils.toJSONString(productPriceService.getList(productPriceVO));
        }
    }

    /*
     *  查询产品列表模板
     * */
    @RequestMapping("/getProductPriceTempList")
    String getProductPriceTempList(@RequestBody Factory factory){
        logger.info("request param Factory : "+factory);
        ProductType productType = new ProductType();
        productType.setFactoryType(factory.getFactoryType());

        List<ProductPrice> productList = productPriceService.getTemplateList(productType);

        return ResponseUtils.toJSONString(productList);
    }

    /*
    *  查询用户的工厂和产品列表
    * */
    @RequestMapping("/getFactoryList")
    String getFactories(@RequestBody Factory factory){

        logger.info("request param Factory : "+ factory);

        User userInfo = RequestUtils.getUserInfo();
        if(factory.getOnwer() != null){
            factory.setOnwer(userInfo);
        } else if(factory.getSaler() != null){
            factory.setSaler(userInfo);
        }
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
        logger.info("request param Factory : "+factory);
        ProductType productType = new ProductType();
        productType.setFactoryType(factory.getFactoryType());

        List<ProductType> productTypeList = factoryService.getProductTypeList(productType);

        return ResponseUtils.toJSONString(productTypeList);
    }





    /*
     *  删除工厂
     * */
    @RequestMapping("/deleteFactory")
    public void deleteFactory(@RequestBody Factory factory){
        logger.info("request param Factory : "+factory);
        factoryService.deleteById(factory);
    }

    /*
     *  保存或更新工厂
     * */
    @RequestMapping("/saveOrUpdateFactory")
    public void saveOrUpdateFactory(@RequestBody Factory factory){
        logger.info("request param Factory : "+factory);
        factoryService.saveOrUpdateFactory(factory);
    }
}
