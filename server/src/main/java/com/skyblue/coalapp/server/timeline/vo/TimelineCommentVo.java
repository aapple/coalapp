package com.skyblue.coalapp.server.timeline.vo;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Setter
@Getter
public class TimelineCommentVo {

    private Integer timeline_id;

    private Integer timeline_comment_id;

    private String content;

    private User author;
}
