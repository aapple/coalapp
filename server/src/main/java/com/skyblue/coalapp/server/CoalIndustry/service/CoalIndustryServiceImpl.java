package com.skyblue.coalapp.server.CoalIndustry.service;
;
import com.skyblue.coalapp.server.CoalIndustry.domain.Factory;
import com.skyblue.coalapp.server.CoalIndustry.repository.CoalIndustryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Service
public class CoalIndustryServiceImpl implements CoalIndustryService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CoalIndustryRepository coalIndustryRepository;

    @Override
    public List<Factory> findAllByName(String name) {
        logger.info("find coal industries by name");

        List<Factory> industries = coalIndustryRepository.findAllByName(name);

        return industries;
    }

    @Override
    public Factory findByCode(String code) {
        return coalIndustryRepository.findByCode(code);
    }

    @Override
    public void save(Factory industry) {
        coalIndustryRepository.save(industry);
    }

    @Override
    public void deleteByCode(String code) {

        Factory industry = new Factory();
        industry.setCode(code);

        coalIndustryRepository.delete(industry);
    }

    public List<Factory> getFactoryProductsList(){

        List<Factory> result = new ArrayList<Factory>();

        Iterable<Factory> factories = coalIndustryRepository.findAll();

        factories.forEach(new Consumer<Factory>(){
            @Override
            public void accept(Factory factory) {
                if(factory.getProducts().size()>0){
                    result.add(factory);
                }
            }
        });

        return result;
    }
}