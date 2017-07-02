package com.dayuzl.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "systems")
@Setter
@Getter
public class Systems {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String community_name;

    @Column
    private Integer enable_wechat_pa = 0;

    @Column
    private Integer wx_app_id;

    @Column
    private Integer wx_app_secret;

    @Column
    private Integer wx_temp_notice_id;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="datetime")
    private Date created_at;

}
