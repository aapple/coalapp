package com.skyblue.coalapp.server.timeline.service;

import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.timeline.domain.Timeline_imgs;
import com.skyblue.coalapp.server.timeline.domain.Timeline_likes;
import com.skyblue.coalapp.server.timeline.domain.Timelines;
import com.skyblue.coalapp.server.timeline.vo.TimelineCommentVo;

import java.util.List;

public interface TimelinesService {


    Timelines saveTimelines(Timelines timelines);

    Timeline_likes saveTimelineLikes(Timeline_likes timelineLikes);

    Timeline_imgs saveTTimelineImgs(Timeline_imgs timelineImgs);

    List<Timelines> findAllTimelines(String type, Integer id);

    void deleteById(Timelines timelines);

    Timelines saveComment(TimelineCommentVo timelineCommentVo);
}
