package com.dayuzl.coalapp.server.product.service;

import com.dayuzl.coalapp.server.product.domain.ProductPriceHis;
import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import com.dayuzl.coalapp.server.product.domain.ProductType;
import com.dayuzl.coalapp.server.product.repository.ProductPriceHisRepository;
import com.dayuzl.coalapp.server.product.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private ProductPriceHisRepository productPriceHisRepository;

    @Override
    @CacheEvict(value="productPriceList",allEntries=true)
    public void saveOrUpdateProductPrice(ProductPrice productPrice){

        ProductPrice productPriceTmp = new ProductPrice();
        productPriceTmp.setFactory(productPrice.getFactory());
        productPriceTmp.setProductType(productPrice.getProductType());

        List<ProductPrice> productPrices = getProductPriceList(productPriceTmp);

        //update
        if(productPrices != null && productPrices.size()> 0){
            productPriceTmp = productPrices.get(0);
            if(productPrice.getPrice2() == null){

                BigDecimal priceDiff = productPrice.getPrice().divide(productPriceTmp.getPrice());
                productPrice.setId(productPriceTmp.getId());
                productPrice.setPriceDiff(priceDiff);
            } else {
                productPriceTmp.setPrice2(productPrice.getPrice2());
                productPrice = productPriceTmp;
            }
        }else{
            //add
            productPrice.setCreatedTime(new Date());
        }

        productPrice.setState(1);

        productPriceRepository.save(productPrice);
    }

    @Override
    @Cacheable(value = "productPriceList", key="#productPrice.toString()",unless="!(#result.size()>0)")
    public List<ProductPrice> getProductPriceList(ProductPrice productPrice){

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()//构建对象
                .withMatcher("factory.name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

        //创建实例
        Example<ProductPrice> ex = Example.of(productPrice, matcher);

        //查询
        List<ProductPrice> productPrices = productPriceRepository.findAll(ex);

        return productPrices;
    }

    @Override
    @Cacheable(value = "prodcutPriceTemplateList", key="#productType.toString()",unless="!(#result.size()>0)")
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

    @Scheduled(cron = "0 58 23 * * ?")
    public void recordProductPriceToHistoryPrice(){
        ProductPrice productPrice = new ProductPrice();
        //productPrice.setUpdateTime(new Date());

        //查找当前表中的所有合适的数据
        List<ProductPrice> productPrices = getProductPriceList(productPrice);

        if(productPrices != null && productPrices.size()>0){

            List<ProductPriceHis> productPriceHisList = new ArrayList<ProductPriceHis>();

            for(ProductPrice prodPrice: productPrices){
                prodPrice.setId(null);

                productPriceHisList.add(prodPrice.toProductPriceHis());
            }

            if(productPrices.size()>0){
                productPriceHisRepository.save(productPriceHisList);
            }
        }
    }
}
