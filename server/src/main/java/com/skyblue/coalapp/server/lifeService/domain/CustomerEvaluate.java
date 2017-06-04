package com.skyblue.coalapp.server.lifeService.domain;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_evaluate")
@Setter
@Getter
public class CustomerEvaluate {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private User customer;

    @ManyToOne
    private LifeServiceInfo lifeServiceInfo;

    @Column(nullable = false,length = 1024)
    private String evaluate;

    @Column(length = 1024)
    private String images;

    //1-5
    @Column(length = 1)
    private Integer grade;

    @Column(columnDefinition="datetime")
    private Date createdTime;
}
