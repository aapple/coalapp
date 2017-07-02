package com.dayuzl.coalapp.server.timeline.vo;

import com.dayuzl.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TimelineCommentVo {

    private Integer timeline_id;

    private Integer timeline_comment_id;

    private String content;

    private User author;
}
