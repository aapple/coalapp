package com.dayuzl.coalapp.server.timeline.domain;

import com.dayuzl.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "timeline_comments")
@Setter
@Getter
public class Timeline_comments {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer timeline_id;

    @Column(nullable = false)
    private Integer user_id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Timeline_comments parents;

    @Column
    private Integer parent_id = 0;

    @Column
    private String content;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="datetime")
    private Date created_at;

}
