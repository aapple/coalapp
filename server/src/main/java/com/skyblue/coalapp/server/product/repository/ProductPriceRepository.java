package com.skyblue.coalapp.server.product.repository;

import com.skyblue.coalapp.server.product.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

}
