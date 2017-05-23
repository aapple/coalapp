package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "topics")
@Setter
@Getter
public class Topics {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer user_id;

    @Column
    private Integer topic_node_id;

    @Column
    private String title;

    @Column(length = 10000)
    private String text;

    @Column
    private Integer star_num = 0;

    @Column
    private Integer thumb_up_num = 0;

    @Column
    private Integer thumb_down_num = 0;

    @Column
    private Integer view_num = 0;

    @Column
    private Integer comment_num = 0;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT 0 ")
    private Date created_at;
}
