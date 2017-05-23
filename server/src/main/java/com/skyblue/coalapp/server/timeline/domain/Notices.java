package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "notices")
@Setter
@Getter
public class Notices {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private Integer initiator_user_id;

    @Column(nullable = false)
    private Integer entity_id;

    @Column(nullable = false)
    private String entity_type;

    @Column(nullable = false)
    private Integer type_id;

    @Column
    private Integer target_type;

    @Column
    private Integer target_id;

    @Column
    private Integer is_checked = 0;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT 0 ")
    private Date created_at;

}
