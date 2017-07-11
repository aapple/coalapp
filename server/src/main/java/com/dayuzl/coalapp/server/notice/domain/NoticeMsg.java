package com.dayuzl.coalapp.server.notice.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notice_msg")
@Getter
@Setter
public class NoticeMsg {

    @Id
    @GeneratedValue
    private Integer id;

    //内容
    @Column(nullable = false,length = 2048)
    private String content;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;


}
