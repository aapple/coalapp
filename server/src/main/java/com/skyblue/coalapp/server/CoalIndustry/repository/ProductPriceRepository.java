package com.skyblue.coalapp.server.CoalIndustry.repository;

import com.skyblue.coalapp.server.CoalIndustry.domain.ProductPrice;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    public List<ProductPrice> findAllByFactoryCode(String factoryCode);

    public List<ProductPrice> findAllByProductCodeEqualsOrderByProdPriceAsc(String productCode);

    public List<ProductPrice> findAllByProductTypeEqualsOrderByProdPriceAsc(Integer productType);



}
