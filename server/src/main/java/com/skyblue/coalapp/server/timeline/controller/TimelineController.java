package com.skyblue.coalapp.server.timeline.controller;

import com.skyblue.coalapp.server.framework.util.RequestUtils;
import com.skyblue.coalapp.server.framework.util.ResponseUtils;
import com.skyblue.coalapp.server.timeline.domain.Timeline_imgs;
import com.skyblue.coalapp.server.timeline.domain.Timeline_likes;
import com.skyblue.coalapp.server.timeline.domain.Timelines;
import com.skyblue.coalapp.server.timeline.service.TimelinesService;
import com.skyblue.coalapp.server.timeline.vo.TimelineCommentVo;
import com.skyblue.coalapp.server.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yao on 2017/5/21.
 */
@RestController
@RequestMapping("app/")
public class TimelineController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TimelinesService timelinesService;

    @RequestMapping("/timeline")
    String timeline(String type, Integer id){

        return ResponseUtils.toJSONString(timelinesService.findAllTimelines(type, id));
    }

    @RequestMapping("/timeline/setLike")
    String setLike(@RequestBody Timelines timelines){

        User userInfo = RequestUtils.getUserInfo();
        Boolean isLike = timelines.getIs_like();
        if(isLike == true){

            List<Timeline_likes> author_like = timelines.getAuthor_like();

            timelines.setIs_like(false);
            timelines.setLike_num(author_like.size());
        } else {

            List<Timeline_likes> author_like = timelines.getAuthor_like();

            Timeline_likes timelineLikes = new Timeline_likes();
            timelineLikes.setCreated_at(new Date());
            timelineLikes.setAuthor(userInfo);
            timelineLikes.setUser_id(userInfo.getId());
            timelineLikes.setTimeline_id(timelines.getId());

            timelineLikes = timelinesService.saveTimelineLikes(timelineLikes);
            timelines.getAuthor_like().add(timelineLikes);
            timelines.setIs_like(false);
            timelines.setLike_num(author_like.size());
        }

        timelines = timelinesService.saveTimelines(timelines);
        timelines.setIs_like(!isLike);
        timelines.setLike_num(timelines.getAuthor_like().size());
        timelines.setComment_num(timelines.getComments().size());
        return ResponseUtils.toJSONString(timelines);
    }

    @RequestMapping("timeline/store")
    String store(@RequestBody Timelines timelines){

        User userInfo = RequestUtils.getUserInfo();
        timelines.setUser_id(userInfo.getId());
        timelines.setAuthor(userInfo);
        timelines.setCreated_at(new Date());

        if(timelines.getImgs() != null && timelines.getImgs().size() > 0){
            List<Timeline_imgs> imgs = new ArrayList<Timeline_imgs>();
            for(Timeline_imgs timelineImgs : timelines.getImgs()){
                timelineImgs.setCreated_at(new Date());
                timelineImgs.setUser_id(userInfo.getId());

                imgs.add(timelinesService.saveTTimelineImgs(timelineImgs));
            }
            timelines.setImgs(imgs);;
        }

        timelines = timelinesService.saveTimelines(timelines);
        return ResponseUtils.toJSONString(timelines);
    }

    @RequestMapping("/timeline/update")
    String update(){

        return "";
    }

    @RequestMapping("/timeline/destroy")
    String destroy(@RequestBody Timelines timelines){

        timelinesService.deleteById(timelines);
        List<String> result = new ArrayList<String>();
        result.add("success");
        return ResponseUtils.toJSONString(result);
    }

    @RequestMapping("/timeline/store-comment")
    String storeComment(@RequestBody TimelineCommentVo timelineCommentVo){

        User userInfo = RequestUtils.getUserInfo();
        timelineCommentVo.setAuthor(userInfo);
        Timelines timelines = timelinesService.saveComment(timelineCommentVo);

        return ResponseUtils.toJSONString(timelines);
    }

    @RequestMapping("/timeline/store-img")
    String storeImg(){

        return "";
    }

    @RequestMapping("/timeline/store-video")
    String storeVideo(){

        return "";
    }

}
