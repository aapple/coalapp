package com.skyblue.coalapp.server.CoalIndustry.Controller;

import com.alibaba.fastjson.JSON;
import com.skyblue.coalapp.server.example.domain.Factory;
import com.skyblue.coalapp.server.example.domain.ProductPrice;
import com.skyblue.coalapp.server.example.service.CoalIndustryService;
import com.skyblue.coalapp.server.example.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/getProdList")
    @ResponseBody
    public String getSpecFacoryCurrProds(@RequestParam(value = "factoryCode")String factoryCode, HttpServletResponse response){

        //查询当前厂可供应商品的信息
        List<ProductPrice> productList = productService.getProductListByCode(factoryCode,true);

        return JSON.toJSONString(productList);
    }

    @RequestMapping("/saveProdList")
    @ResponseBody
    public void saveOrUpdateProdutrice(@RequestParam(value = "productList") String productList,HttpServletResponse response){

        if(StringUtils.isNoneEmpty(productList)){
            List<ProductPrice> prodList = JSON.parseArray(productList,ProductPrice.class);

            if(null != productList && prodList.size()>0){
                productService.saveOrUpdateProdcutList(prodList);
            }
        }
    }

    public void getAllCurrntProductPrice(){
        List<Factory> prodcutList = coalIndustryService.getFactoryProductsList();
    }
}
