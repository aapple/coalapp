package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.ProductPrice;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.product.domain.ProductType_old;
import com.skyblue.coalapp.server.product.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private FactoryService factoryService;

    public void saveOrUpdateProductPrice(ProductPrice productPrice){

        // 首先判断是不是有ID
        if(productPrice.getId() == null){
            List<ProductPrice> productPrices = getProductPriceList(productPrice);
            if(productPrices != null && productPrices.size()> 0){
                productPrice = productPrices.get(0);
            }else{
                productPrice.setCreatedTime(new Date());
                productPrice.setState(1);
            }
        }

        productPriceRepository.save(productPrice);
    }

    public List<ProductPrice> getProductPriceList(ProductPrice productPrice){

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<ProductPrice> ex = Example.of(productPrice, matcher);

        //查询
        List<ProductPrice> productPrices = productPriceRepository.findAll(ex);

        return productPrices;
    }

    public List<ProductPrice> getProdcutPriceTemplateList(ProductType productType){

        List<ProductType> productTypes =  factoryService.getProductTypeList(productType);

        List<ProductPrice> productPrices = new ArrayList<ProductPrice>();
        if(productTypes != null){
            for(ProductType prodType : productTypes){

                ProductPrice productPrice = new ProductPrice();
                productPrice.setProductType(productType);
            }
        }

        return productPrices;
    }
}
