package com.skyblue.coalapp.server.timeline.domain;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne
    private User author;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="datetime")
    private Date created_at;

}
