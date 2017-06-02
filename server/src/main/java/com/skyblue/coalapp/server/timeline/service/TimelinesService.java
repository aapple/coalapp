package com.skyblue.coalapp.server.timeline.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.timeline.domain.Timelines;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface TimelinesService {


    Timelines saveTimelines(Timelines timelines);
}
