package com.dayuzl.coalapp.server.dailynews.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "daily_news")
@Getter
@Setter
public class DailyNews {

    @Id
    @GeneratedValue
    private Integer id;

    //标题
    @Column(nullable = false)
    private String title ;

    //内容
    @Column(nullable = false)
    private String content ;

    //配图
    @Column
    private String image;

    //发布人
    @Column
    private String publisher;

    //发布时间
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
