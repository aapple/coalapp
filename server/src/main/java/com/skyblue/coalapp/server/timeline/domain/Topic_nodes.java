package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "topic_nodes")
@Setter
@Getter
public class Topic_nodes {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column(length = 10000)
    private String description;

    @Column
    private Integer parent_id;

    @Column
    private Integer lft;

    @Column
    private Integer rgt;

    @Column
    private Integer depth;

}
