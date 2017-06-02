package com.skyblue.coalapp.server.timeline.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.product.repository.FactoryRepository;
import com.skyblue.coalapp.server.product.repository.ProductTypeRepository;
import com.skyblue.coalapp.server.product.service.FactoryService;
import com.skyblue.coalapp.server.timeline.domain.Timelines;
import com.skyblue.coalapp.server.timeline.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Service
public class TimelinesServiceImpl implements TimelinesService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TimelineCommentsRepository timelineCommentsRepository;

    @Autowired
    private TimelineImgsRepository timelineImgsRepository;

    @Autowired
    private TimelineLikesRepository timelineLikesRepository;

    @Autowired
    private TimelinesRepository timelinesRepository;

    @Autowired
    private TimelineVideosRepository timelineVideosRepository;


    @Override
    public Timelines saveTimelines(Timelines timelines) {

        return timelinesRepository.save(timelines);
    }
}
