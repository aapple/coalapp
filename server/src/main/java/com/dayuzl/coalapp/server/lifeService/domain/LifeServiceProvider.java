package com.dayuzl.coalapp.server.lifeService.domain;

import com.alibaba.fastjson.JSON;
import com.dayuzl.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "life_service_provider")
@Getter
@Setter
public class LifeServiceProvider {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column (length = 1024)
    private String introduction;

    @Column(nullable =false)
    private String serviceType;

    @Column(nullable= false)
    private String address;

    @Column
    private String callPerson;

    @Column
    private String callNumber;

    @Column
    private String point;

    @Column
    private String area;

    @Column
    private String photoPath;

    @Column(length = 2)
    private Integer state;

    @ManyToOne
    private User owner;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
