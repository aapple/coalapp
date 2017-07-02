package com.dayuzl.coalapp.server.Information.repository;

import com.dayuzl.coalapp.server.Information.domain.LogisticsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LogisticsInfo Repository
 */
@Repository
public interface LogisticsInfoRepository extends JpaRepository<LogisticsInfo,Long> {
}
