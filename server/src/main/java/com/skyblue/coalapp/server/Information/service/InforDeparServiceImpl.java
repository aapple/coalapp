package com.skyblue.coalapp.server.Information.service;

import com.skyblue.coalapp.server.Information.domain.InfoDepartment;
import com.skyblue.coalapp.server.Information.repository.InfoDepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InforDeparServiceImpl implements InfoDepartService {

    @Autowired
    private InfoDepartRepository infoDepartRepository;

    @Override
    public void addInfoDepartment(InfoDepartment infoDepart) {
        if(infoDepart != null){
            infoDepartRepository.save(infoDepart);
        }
    }

    @Override
    public InfoDepartment findOne(InfoDepartment infoDepart) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<InfoDepartment> ex = Example.of(infoDepart, matcher);

        return infoDepartRepository.findOne(ex);
    }

    @Override
    public List<InfoDepartment> findAll(InfoDepartment infoDepartment) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<InfoDepartment> ex = Example.of(infoDepartment, matcher);

        return infoDepartRepository.findAll(ex);
    }
}
