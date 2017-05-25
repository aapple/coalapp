package com.skyblue.coalapp.server.CoalIndustry.repository;

import com.skyblue.coalapp.server.CoalIndustry.domain.ProductPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

    public List<ProductPrice> findAllByFactoryCode(String factoryCode);
}
