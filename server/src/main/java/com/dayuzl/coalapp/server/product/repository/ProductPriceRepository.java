package com.dayuzl.coalapp.server.product.repository;

import com.dayuzl.coalapp.server.product.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

    @Modifying
    @Transactional
    Long deleteByFactoryIdAndProductTypeIdAndPriceOwnerTypeAndCoalWashingAndGraded(@Param("factoryId") Integer factoryId,
                                                                                   @Param("productTypeId") Integer productTypeId,
                                                                                   @Param("priceOwnerType") Integer priceOwnerType,
                                                                                   @Param("coalWashing") Integer coalWashing,
                                                                                   @Param("graded") Integer graded);
}
