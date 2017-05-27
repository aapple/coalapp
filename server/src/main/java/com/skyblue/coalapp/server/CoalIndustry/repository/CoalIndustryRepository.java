package com.skyblue.coalapp.server.CoalIndustry.repository;

import com.skyblue.coalapp.server.CoalIndustry.domain.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface CoalIndustryRepository extends JpaRepository<Factory, Long> {

    Factory findByCode(String code);

    List<Factory> findAllByName(String name);

    List<Factory> findAllByOwnerCodeEquals(String ownerCode);

    @Override
    <S extends Factory> S save(S s);

    @Override
    void delete(Factory coalIndustry);


}
