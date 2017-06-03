package com.skyblue.coalapp.server.timeline.domain;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "timelines")
@Setter
@Getter
public class Timelines {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer user_id;

    @ManyToOne
    private User author;

    @OneToMany
    private List<Timeline_comments> comments;

    @OneToMany
    private List<Timeline_likes> author_like;

    @OneToMany
    private List<Timeline_imgs> imgs;

    @OneToOne
    private Timeline_videos video;

    @Column(length = 10000)
    private String content;

    @Transient
    private Integer like_num;

    @Column(columnDefinition="int(11) DEFAULT 0")
    private Integer view_num;

    @Transient
    private Integer comment_num;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="datetime")
    private Date created_at;

    @Transient
    private Boolean is_like = false;


}
