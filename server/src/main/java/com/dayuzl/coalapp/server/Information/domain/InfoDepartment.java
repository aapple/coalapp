package com.dayuzl.coalapp.server.Information.domain;

import com.alibaba.fastjson.JSON;
import com.dayuzl.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 信息部
 */

@Entity
@Table(name = "info_department")
@Getter
@Setter
public class InfoDepartment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 128)
    private String title;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length = 1024)
    private String introduction;

    @Column
    private String callPerson;

    @Column
    private String callNumber;

    @Column(length = 128)
    private String point;

    @Column(nullable = false,length = 128)
    private String address;

    @Column(length = 256)
    private String photoPath;

    @Column(columnDefinition="INT default 0")
    private Integer priority;

    //更新时间
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
