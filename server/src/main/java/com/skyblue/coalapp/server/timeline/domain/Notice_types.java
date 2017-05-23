package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "notice_types")
@Setter
@Getter
public class Notice_types {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;


//    $noticeTypes = [
//            ['id' => 10, 'name' => 'timeline_like'],
//            ['id' => 11, 'name' => 'timeline_comment'],
//            ['id' => 12, 'name' => 'timeline_comment_comment'],
//            ['id' => 20, 'name' => 'topic_like'],
//            ['id' => 21, 'name' => 'topic_comment'],
//            ['id' => 22, 'name' => 'topic_comment_comment'],
//            ];
}
