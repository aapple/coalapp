package com.dayuzl.coalapp.server.dailynews.repository;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyNewsRepository extends JpaRepository<DailyNews,Long> {

}
