package com.skyblue.coalapp.server.example.service;

import com.skyblue.coalapp.server.LogIn.vo.FactoryVO;
import com.skyblue.coalapp.server.example.domain.Factory;
import com.skyblue.coalapp.server.example.domain.ProductPrice;
import com.skyblue.coalapp.server.example.domain.ProductType;
import com.skyblue.coalapp.server.example.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private CoalIndustryService coalIndustryService;

    @Override
    public List<ProductPrice> getProductListByCode(String factoryCode, boolean includeAll) {

        //1. 获取厂商信息
        Factory factory = coalIndustryService.findByCode(factoryCode);

        List<ProductPrice> ProductPriceListResult = new ArrayList<ProductPrice>();
        if(null != factory){
            Integer type = factory.getType();

            // 获取当前类型所有的产品
            List<ProductType> productTypeList = ProductType.getThisTypeAllProdcutList(type);

            //获取当前厂商的所有产品
            List<ProductPrice> ProductPriceList = productPriceRepository.findAllByFactoryCode(factoryCode);

            //如果需要显示所有类型的产品
            if(includeAll){

                if(productTypeList != null && productTypeList.size() > 0 && ProductPriceList != null && ProductPriceList.size()>0) {
                    Map<String,ProductPrice> map = new HashMap<String,ProductPrice>();

                    for(ProductType p : productTypeList){
                        ProductPrice productPrice = new ProductPrice(p,factoryCode);
                        map.put(p.getCode(),productPrice);
                    }

                    for (ProductPrice price : ProductPriceList){
                        String code = price.getProductCode();
                        map.replace(code,price);
                    }

                    for(ProductType p : productTypeList){
                        ProductPriceListResult.add(map.get(p.getCode()));
                    }
                }
            }else{
                ProductPriceListResult = ProductPriceList;
            }
        }

        return ProductPriceListResult;
    }

    public void saveOrUpdateProdcutList(List<ProductPrice> productList){

        if(productList != null && productList.size()>0){
            List<ProductPrice> products = new ArrayList<ProductPrice>();
            for(ProductPrice p : productList){
                if(p.getProdPrice().doubleValue()>0){
                    products.add(p);
                }
            }
            productPriceRepository.save(products);
        }
    }
}
