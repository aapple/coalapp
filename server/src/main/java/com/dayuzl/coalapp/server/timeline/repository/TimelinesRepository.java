package com.dayuzl.coalapp.server.timeline.repository;

import com.dayuzl.coalapp.server.timeline.domain.Timelines;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelinesRepository extends JpaRepository<Timelines, Long> {

    @Query("select t from Timelines t where timelineType=?1")
    List<Timelines> findAllTimelines(String timelineType, Pageable pageable);

    @Query("select t from Timelines t where t.id > ?1 and timelineType=?2")
    List<Timelines> findAllByRefresh(int id, String timelineType, Pageable page);

    @Query("select t from Timelines t where t.id < ?1  and timelineType=?2")
    List<Timelines> findAllByInfinite(int id, String timelineType, Pageable pageable);

    Timelines findById(Integer id);
}
