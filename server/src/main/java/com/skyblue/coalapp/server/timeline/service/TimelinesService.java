package com.skyblue.coalapp.server.timeline.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.timeline.domain.Timeline_likes;
import com.skyblue.coalapp.server.timeline.domain.Timelines;
import com.skyblue.coalapp.server.timeline.vo.TimelineCommentVo;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface TimelinesService {


    Timelines saveTimelines(Timelines timelines);

    Timeline_likes saveTimelineLikes(Timeline_likes timelineLikes);

    List<Timelines> findAllTimelines(String type, Integer id);

    void deleteById(Timelines timelines);

    Timelines saveComment(TimelineCommentVo timelineCommentVo);
}
