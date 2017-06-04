package com.skyblue.coalapp.server.lifeService.Repository;

import com.skyblue.coalapp.server.lifeService.domain.LifeServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeServiceInfoRepository extends JpaRepository<LifeServiceInfo,Long>{
}
