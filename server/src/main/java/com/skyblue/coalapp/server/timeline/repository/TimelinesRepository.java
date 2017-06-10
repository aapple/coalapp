package com.skyblue.coalapp.server.timeline.repository;

import com.skyblue.coalapp.server.timeline.domain.Timelines;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelinesRepository extends JpaRepository<Timelines, Long> {

    @Query("select t from Timelines t")
    List<Timelines> findAllTimelines(Pageable pageable);

    @Query("select t from Timelines t where t.id > ?1")
    List<Timelines> findAllByRefresh(int id, Pageable page);

    @Query("select t from Timelines t where t.id < ?1 ")
    List<Timelines> findAllByInfinite(int id, Pageable pageable);

    Timelines findById(Integer id);
}
