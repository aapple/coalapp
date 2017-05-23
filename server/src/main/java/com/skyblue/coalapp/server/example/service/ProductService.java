package com.skyblue.coalapp.server.example.service;

import com.skyblue.coalapp.server.LogIn.vo.FactoryVO;
import com.skyblue.coalapp.server.example.domain.ProductPrice;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductService {

    /*
     * 根据factoryCode查找当前供应产品信息
     */
    public List<ProductPrice> getProductListByCode(String factoryCode, boolean includeAll);

    public void saveOrUpdateProdcutList(List<ProductPrice> productList);
}
