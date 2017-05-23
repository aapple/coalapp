package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "timeline_likes")
@Setter
@Getter
public class Timeline_likes {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer timeline_id;

    @Column(nullable = false)
    private Integer user_id;

}
