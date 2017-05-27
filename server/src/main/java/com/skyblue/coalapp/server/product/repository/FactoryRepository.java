package com.skyblue.coalapp.server.product.repository;

import com.skyblue.coalapp.server.product.domain.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {

}
