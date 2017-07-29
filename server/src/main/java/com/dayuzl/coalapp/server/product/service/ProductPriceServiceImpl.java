package com.dayuzl.coalapp.server.product.service;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import com.dayuzl.coalapp.server.framework.domain.PageParam;
import com.dayuzl.coalapp.server.product.domain.ProductPriceHis;
import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import com.dayuzl.coalapp.server.product.domain.ProductType;
import com.dayuzl.coalapp.server.product.repository.ProductPriceHisRepository;
import com.dayuzl.coalapp.server.product.repository.ProductPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private ProductPriceHisRepository productPriceHisRepository;

    @Override
    @CacheEvict(value="productPriceCache",allEntries=true)
    public void save(ProductPrice productPrice){

        if(productPrice.getId() != null){

            ProductPrice productPriceTmp = productPriceRepository.findOne(productPrice.getId());
            if(productPriceTmp != null){
                BigDecimal priceDiff = productPrice.getPrice().subtract(productPriceTmp.getPrice());
                productPrice.setPriceDiff(priceDiff);
            }else{
                logger.error("product :" + productPrice +" is requesting to update data, but cant be found in database.");
            }

        }else{
            productPrice.setCreatedTime(new Date());
            productPrice.setState(1);
        }

        productPriceRepository.save(productPrice);
    }

    @Override
    @Cacheable(value = "productPriceCache", key="#productPrice.toString()",unless="!(#result.size()>0)")
    public List<ProductPrice> getList(ProductPrice productPrice){

        Example<ProductPrice> ex = this.buildExample(productPrice);

        // sort
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");

        //查询
        List<ProductPrice> productPrices = productPriceRepository.findAll(ex,sort);

        return productPrices;
    }

    @Override
    @Cacheable(value = "productPriceCache", key="#productPrice.toString()",unless="!(#result!=null)")
    public Page<ProductPrice> getPage(ProductPrice productPrice){

        Example<ProductPrice> ex = this.buildExample(productPrice);

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageRequest = new PageRequest(productPrice.getPageNumber(), productPrice.getPageSize(), sort);

        Page<ProductPrice> pricePage = productPriceRepository.findAll(ex,pageRequest);

        return pricePage;
    }

    @Override
    public List<ProductPrice> getTemplateList(ProductType productType){

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

    @CacheEvict(value="productPriceCache",allEntries=true)
    public void delete(ProductPrice productPrice){

        productPriceRepository.deleteByFactoryIdAndProductTypeIdAndPriceOwnerTypeAndCoalWashingAndGraded(
                productPrice.getFactory().getId(),
                productPrice.getProductType().getId(),
                productPrice.getPriceOwnerType(),
                productPrice.getCoalWashing(),
                productPrice.getGraded());
    }

    private Example<ProductPrice> buildExample(ProductPrice productPrice){

        ExampleMatcher matcher = ExampleMatcher.matching()//构建对象
                .withMatcher("factory.name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉

         return Example.of(productPrice, matcher);
    }

    @Scheduled(cron = "0 58 23 * * ?")
    public void recordProductPriceToHistoryPrice(){
        ProductPrice productPrice = new ProductPrice();
        //productPrice.setUpdateTime(new Date());

        //查找当前表中的所有合适的数据
        List<ProductPrice> productPrices = getList(productPrice);

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

    @CacheEvict(value="productPriceCache",allEntries=true)
    @Scheduled(fixedDelay = 2*60*1000)
    public void clearCache(){
        logger.info("it's time to clean product price cache");
    }
}
