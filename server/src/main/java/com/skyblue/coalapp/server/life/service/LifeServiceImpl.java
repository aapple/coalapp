package com.skyblue.coalapp.server.life.service;

import com.skyblue.coalapp.server.life.domain.LifeStore;
import com.skyblue.coalapp.server.life.repository.LifeRepository;
import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifeServiceImpl implements LifeService {

    @Autowired
    private LifeRepository lifeRepository;

    @Override
    public void addInfoDepartment(LifeStore infoDepart) {
        lifeRepository.save(infoDepart);
    }

    @Override
    public List<LifeStore> findAll(LifeStore lifeStore) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<LifeStore> ex = Example.of(lifeStore, matcher);

        return lifeRepository.findAll(ex);
    }

    @Override
    public void deleteById(LifeStore lifeStore) {
        lifeRepository.delete(lifeStore);
    }
}
