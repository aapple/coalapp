package com.skyblue.coalapp.server.Information.repository;

import com.skyblue.coalapp.server.Information.domain.InfoDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 张杨 on 2017/5/28.
 */
public interface InfoDepartRepository extends JpaRepository<InfoDepartment, Long> {
}
