package com.skyblue.coalapp.server.CoalIndustry.service;

import com.skyblue.coalapp.server.example.domain.CommonEnum;
import com.skyblue.coalapp.server.example.domain.Factory;
import com.skyblue.coalapp.server.example.domain.ProductPrice;
import com.skyblue.coalapp.server.example.domain.ProductType;
import com.skyblue.coalapp.server.example.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            //获取当前厂商的所有产品
            List<ProductPrice> ProductPriceList = productPriceRepository.findAllByFactoryCode(factoryCode);
            if(ProductPriceList == null){
                ProductPriceList = new ArrayList<ProductPrice>();
            }

            //如果需要显示所有类型的产品
            if(includeAll){
                List<ProductPrice> prodTemplateList = this.getProductPriceTemplateList(type);

                if(prodTemplateList != null && prodTemplateList.size() > 0){

                    Map<String,ProductPrice> map = new HashMap<String,ProductPrice>();
                    for(ProductPrice p : prodTemplateList){
                        p.setFactoryCode(factoryCode);
                        map.put(p.getProductCode(),p);
                    }

                    for (ProductPrice price : ProductPriceList){
                        String code = price.getProductCode();
                        map.replace(code,price);
                    }

                    for(ProductPrice p : prodTemplateList){
                        ProductPriceListResult.add(map.get(p.getProductCode()));
                    }
                }else{
                    ProductPriceListResult = ProductPriceList;
                }
            }
        }

        return ProductPriceListResult;
    }

    public void saveOrUpdateProdcutList(List<ProductPrice> productList){

        //add some validations
        if(productList != null && productList.size()>0){
            List<ProductPrice> products = new ArrayList<ProductPrice>();
            for(ProductPrice p : productList){
                if(p.getProdPrice().doubleValue()>0){
                    p.setState(CommonEnum.STATE_YES.getValue());
                    products.add(p);
                }
            }

            productPriceRepository.save(products);
        }
    }

    public List<ProductPrice> getProductPriceTemplateList(int type){

        // 获取当前类型所有的产品
        List<ProductType> productTypeList = ProductType.getThisTypeAllProdcutList(type);

        List<ProductPrice> prodTemplate = new ArrayList<ProductPrice>();
        for(ProductType p : productTypeList){
            prodTemplate.add(new ProductPrice(p));
        }

        return prodTemplate;
    }
}
