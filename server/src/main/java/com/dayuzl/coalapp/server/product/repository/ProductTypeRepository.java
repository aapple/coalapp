package com.dayuzl.coalapp.server.product.repository;

import com.dayuzl.coalapp.server.product.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
