package com.skyblue.coalapp.server.Information.repository;

import com.skyblue.coalapp.server.Information.domain.LogisticsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LogisticsInfo Repository
 */
public interface LogisticsInfoRepository extends JpaRepository<LogisticsInfo,Long> {
}
