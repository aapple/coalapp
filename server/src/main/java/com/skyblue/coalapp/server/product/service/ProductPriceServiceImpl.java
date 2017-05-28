package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.ProductPrice;
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

    @Override
    public List<ProductPrice> getProductListByFactoryId(Integer factoryId, boolean includeAll) {

//        //1. 获取厂商信息
//        Factory factory = factoryService.findByCode(factoryCode);
//
        List<ProductPrice> ProductPriceListResult = new ArrayList<ProductPrice>();
//
//        if(null != factory){
//            Integer type = factory.factoryType
//
//            //获取当前厂商的所有产品
//            List<ProductPrice> ProductPriceList = productPriceRepository.findAllByFactoryCode(factoryCode);
//            if(ProductPriceList == null){
//                ProductPriceList = new ArrayList<ProductPrice>();
//            }
//
//            //如果需要显示所有类型的产品
//            if(includeAll){
//                List<ProductPrice> prodTemplateList = this.getProductPriceTemplateList(type);
//
//                if(prodTemplateList != null && prodTemplateList.size() > 0){
//
//                    Map<String,ProductPrice> map = new HashMap<String,ProductPrice>();
//                    for(ProductPrice p : prodTemplateList){
//                        p.setFactoryCode(factoryCode);
//                        map.put(p.getProductCode(),p);
//                    }
//
//                    for (ProductPrice price : ProductPriceList){
//                        String code = price.getProductCode();
//                        map.replace(code,price);
//                    }
//
//                    for(ProductPrice p : prodTemplateList){
//                        ProductPriceListResult.add(map.get(p.getProductCode()));
//                    }
//                }else{
//                    ProductPriceListResult = ProductPriceList;
//                }
//            }
//        }

        return ProductPriceListResult;
    }

    public void saveOrUpdateProductPrice(ProductPrice productPrice){

        productPrice.setCreatedTime(new Date());
        productPrice.setState(1);
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

    public List<ProductPrice> getProductPriceTemplateList(int type){

        // 获取当前类型所有的产品
        List<ProductType_old> productTypeOldList = ProductType_old.getThisTypeAllProdcutList(type);

        List<ProductPrice> prodTemplate = new ArrayList<ProductPrice>();
//        for(ProductType p : productTypeList){
//            prodTemplate.add(new ProductPrice(p));
//        }

        return prodTemplate;
    }
}
