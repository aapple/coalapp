package com.skyblue.coalapp.server.timeline.service;

import com.skyblue.coalapp.server.framework.RequestUtils;
import com.skyblue.coalapp.server.product.domain.Factory;
import com.skyblue.coalapp.server.product.domain.ProductType;
import com.skyblue.coalapp.server.product.repository.FactoryRepository;
import com.skyblue.coalapp.server.product.repository.ProductTypeRepository;
import com.skyblue.coalapp.server.product.service.FactoryService;
import com.skyblue.coalapp.server.timeline.domain.Timeline_comments;
import com.skyblue.coalapp.server.timeline.domain.Timeline_likes;
import com.skyblue.coalapp.server.timeline.domain.Timelines;
import com.skyblue.coalapp.server.timeline.repository.*;
import com.skyblue.coalapp.server.timeline.vo.TimelineCommentVo;
import com.skyblue.coalapp.server.user.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Override
    public Timeline_likes saveTimelineLikes(Timeline_likes timelineLikes) {
        return timelineLikesRepository.save(timelineLikes);
    }

    @Override
    public List<Timelines> findAllTimelines(String type, Integer id) {

        List<Timelines> result = null;
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "created_at");
        if(StringUtils.isEmpty(type)) {
            result =  timelinesRepository.findAllTimelines(pageable);
        } else if(StringUtils.equals("refresh", type)) {
            Pageable page = new PageRequest(0, 10000000, Sort.Direction.DESC, "created_at");
            result =  timelinesRepository.findAllByRefresh(id, page);
        } else if(StringUtils.equals("infinite", type)) {
            result =  timelinesRepository.findAllByInfinite(id, pageable);
        }

        // 添加是否喜欢，喜欢数，评论数字段的处理
        User userInfo = RequestUtils.getUserInfo();
        for (Timelines timelines: result) {
            for (Timeline_likes timelineLikes : timelines.getAuthor_like()) {
                if(timelineLikes.getUser_id() == userInfo.getId()){
                    timelines.setIs_like(true);
                    break;
                }
            }
            timelines.setLike_num(timelines.getAuthor_like().size());
            timelines.setComment_num(timelines.getComments().size());
        }

        return result;
    }

    @Override
    public void deleteById(Timelines timelines) {
        timelinesRepository.delete(timelines);
    }

    @Override
    public Timelines saveComment(TimelineCommentVo timelineCommentVo) {

        Timelines timelines = timelinesRepository.findById(timelineCommentVo.getTimeline_id());
        Timeline_comments timelineComments = new Timeline_comments();
        timelineComments.setContent(timelineCommentVo.getContent());
        timelineComments.setCreated_at(new Date());
        timelineComments.setUser_id(timelineCommentVo.getAuthor().getId());
        timelineComments.setAuthor(timelineCommentVo.getAuthor());

        if(timelineCommentVo.getTimeline_comment_id() != null){
            timelineComments.setParent_id(timelineCommentVo.getTimeline_comment_id());
            Timeline_comments parent = timelineCommentsRepository
                    .findById(timelineCommentVo.getTimeline_comment_id());

            timelineComments.setParents(parent);
        }

        timelineComments.setTimeline_id(timelineCommentVo.getTimeline_id());
        timelineComments = timelineCommentsRepository.save(timelineComments);

        List<Timeline_comments> comments = timelines.getComments();
        comments.add(timelineComments);
        timelines.setComments(comments);

        return timelinesRepository.save(timelines);
    }
}
