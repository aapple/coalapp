package com.dayuzl.coalapp.server.lifeService.Repository;

import com.dayuzl.coalapp.server.lifeService.domain.CustomerEvaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEvaluateRepository extends JpaRepository<CustomerEvaluate,Long> {
}
