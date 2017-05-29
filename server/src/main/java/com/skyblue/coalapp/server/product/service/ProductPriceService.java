package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.ProductPrice;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductPriceService {

    /*
     * 保存或更新产品信息
     */
    void saveOrUpdateProductPrice(ProductPrice productPrice);

    /*
   * 获取某类型产品信息列表
   */
    List<ProductPrice> getProductPriceList(ProductPrice productPrice);
}
