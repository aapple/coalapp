package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.ProductPrice;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductPriceService {

    /*
     * 根据factoryId查找当前供应产品信息 includeAll：是否显示当前产品类型所有产品
     */
    List<ProductPrice> getProductListByFactoryId(Integer factoryId, boolean includeAll);

    /*
     * 保存或更新产品信息
     */
    void saveOrUpdateProductPrice(ProductPrice productPrice);

    /*
    * 获取某类型产品 模板列表
    */
    List<ProductPrice> getProductPriceTemplateList(int type);

    /*
   * 获取某类型产品信息列表
   */
    List<ProductPrice> getProductPriceList(ProductPrice productPrice);
}
