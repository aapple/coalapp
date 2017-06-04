package com.skyblue.coalapp.server.life.repository;

import com.skyblue.coalapp.server.life.domain.LifeStore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 张杨 on 2017/5/28.
 */
public interface LifeRepository extends JpaRepository<LifeStore, Long> {
}
