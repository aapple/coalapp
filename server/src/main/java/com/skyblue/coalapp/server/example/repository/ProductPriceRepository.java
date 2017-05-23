package com.skyblue.coalapp.server.example.repository;

import com.skyblue.coalapp.server.example.domain.ProductPrice;
import com.skyblue.coalapp.server.example.domain.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

    public List<ProductPrice> findAllByFactoryCode(String factoryCode);
}
