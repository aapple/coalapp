package com.dayuzl.coalapp.server.Information.repository;

import com.dayuzl.coalapp.server.Information.domain.InfoDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoDepartRepository extends JpaRepository<InfoDepartment, Long> {
}
