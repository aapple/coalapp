package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(length = 10000)
    private String content;

    @Column
    private String imgs;

    @Column
    private String video;

    @Column
    private String poster;

    @Column
    private Integer like_num;

    @Column
    private Integer view_num;

    @Column
    private Integer comment_num;


}
