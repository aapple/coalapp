package com.skyblue.coalapp.server.product.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface FactoryService {

    List<Factory> getFactoryList(Factory factory);

    void save(Factory industry);

    void deleteById(Integer id);

    List<ProductType> getProductTypeList(ProductType productType);
}
