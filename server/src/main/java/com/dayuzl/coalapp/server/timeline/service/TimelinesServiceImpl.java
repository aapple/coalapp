package com.dayuzl.coalapp.server.timeline.service;

import com.dayuzl.coalapp.server.framework.util.RequestUtils;
import com.dayuzl.coalapp.server.timeline.domain.Timeline_comments;
import com.dayuzl.coalapp.server.timeline.domain.Timeline_likes;
import com.dayuzl.coalapp.server.timeline.repository.*;
import com.dayuzl.coalapp.server.user.domain.User;
import com.dayuzl.coalapp.server.timeline.domain.Timeline_imgs;
import com.dayuzl.coalapp.server.timeline.domain.Timelines;
import com.dayuzl.coalapp.server.timeline.repository.*;
import com.dayuzl.coalapp.server.timeline.vo.TimelineCommentVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TimelinesServiceImpl implements TimelinesService {

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
    public Timeline_imgs saveTTimelineImgs(Timeline_imgs timelineImgs) {
        return timelineImgsRepository.save(timelineImgs);
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
