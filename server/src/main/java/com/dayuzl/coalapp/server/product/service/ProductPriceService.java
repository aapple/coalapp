package com.dayuzl.coalapp.server.product.service;

import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import com.dayuzl.coalapp.server.product.domain.ProductType;

import java.util.List;


public interface ProductPriceService {

    /*
     * 保存或更新产品信息
     */
    void saveOrUpdateProductPrice(ProductPrice productPrice);

    /*
     * 获取某类型产品信息列表
     */
    List<ProductPrice> getProductPriceList(ProductPrice productPrice);

    List<ProductPrice> getProdcutPriceTemplateList(ProductType productType);

    void recordProductPriceToHistoryPrice();
}
