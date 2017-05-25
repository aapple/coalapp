package com.skyblue.coalapp.server.CoalIndustry.service;

import com.skyblue.coalapp.server.example.domain.ProductPrice;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductService {

    /*
     * 根据factoryCode查找当前供应产品信息 includeAll：是否显示当前产品类型所有产品
     */
    public List<ProductPrice> getProductListByCode(String factoryCode, boolean includeAll);

    /*
     * 保存或更新产品信息
     */
    public void saveOrUpdateProdcutList(List<ProductPrice> productList);

    /*
    * 获取某类型产品 模板列表
    */
    public List<ProductPrice> getProductPriceTemplateList(int type);
}
