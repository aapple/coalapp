package com.dayuzl.coalapp.server.example.repository;

import com.dayuzl.coalapp.server.example.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yaobin on 2017/4/28.
 */

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    City findByName(String name);
}
