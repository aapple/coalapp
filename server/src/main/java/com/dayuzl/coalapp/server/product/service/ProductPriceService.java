package com.dayuzl.coalapp.server.product.service;

import com.dayuzl.coalapp.server.framework.domain.PageParam;
import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import com.dayuzl.coalapp.server.product.domain.ProductType;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductPriceService {

    /*
     * 保存或更新产品信息
     */
    void save(ProductPrice productPrice);

    /*
     * 获取某类型产品信息列表
     */
    List<ProductPrice> getList(ProductPrice productPrice);

    Page<ProductPrice> getPage(ProductPrice productPrice);

    List<ProductPrice> getTemplateList(ProductType productType);

    void delete(ProductPrice productPrice);

    void recordProductPriceToHistoryPrice();
}
