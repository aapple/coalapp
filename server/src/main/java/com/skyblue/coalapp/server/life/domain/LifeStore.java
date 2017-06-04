package com.skyblue.coalapp.server.life.domain;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 信息部
 */

@Entity
@Table(name = "life_store")
@Getter
@Setter
public class LifeStore {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String serviceType;

    @Column
    private String area;

    @Column
    private String name;

    @Column(length = 10000)
    private String introduction;

    @Column
    private String callNumber;

    @Column(length = 128)
    private String point;

    @Column
    private String address;

    @Column(length = 256)
    private String photoPath;


    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;
}
