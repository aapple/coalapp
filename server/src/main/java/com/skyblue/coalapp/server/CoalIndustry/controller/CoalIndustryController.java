package com.skyblue.coalapp.server.CoalIndustry.controller;

import com.alibaba.fastjson.JSON;

import com.skyblue.coalapp.server.CoalIndustry.domain.Factory;
import com.skyblue.coalapp.server.CoalIndustry.domain.ProductPrice;
import com.skyblue.coalapp.server.CoalIndustry.service.CoalIndustryService;
import com.skyblue.coalapp.server.CoalIndustry.service.ProductService;
import com.skyblue.coalapp.server.CoalIndustry.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
@RestController
@RequestMapping("/coalIndustry")
public class CoalIndustryController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CoalIndustryService coalIndustryService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/getProdListByFactory")
    public String getSpecFacoryCurrProds(@RequestParam(value = "factoryCode") String factoryCode){

        //查询当前厂可供应商品的信息
        List<ProductPrice> productList = productService.getProductListByCode(factoryCode,true);

        return JSON.toJSONString(productList);
    }

    @RequestMapping("/saveProdList")
    @ResponseBody
    public void saveOrUpdateProdutrice(@RequestParam(value = "productList") String productList){

        if(StringUtils.isNoneEmpty(productList)){
            List<ProductPrice> prodInfoList = JSON.parseArray(productList,ProductPrice.class);

            if(null != prodInfoList && prodInfoList.size()>0){
                productService.saveOrUpdateProdcutList(prodInfoList);
            }
        }
    }

    /*
    *  可以按照条件来查询
    * */
    @RequestMapping("/getCurrentProdList")
    @ResponseBody
    public String getAllProductBy(@RequestBody ProductVO product){

        List<Factory> prodcutList = coalIndustryService.getFactoryProductsList();

        return JSON.toJSONString(prodcutList);
    }
}
