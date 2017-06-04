package com.skyblue.coalapp.server.product.repository;

import com.skyblue.coalapp.server.product.domain.ProductPriceHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceHisRepository extends JpaRepository<ProductPriceHis,Long>{
}
