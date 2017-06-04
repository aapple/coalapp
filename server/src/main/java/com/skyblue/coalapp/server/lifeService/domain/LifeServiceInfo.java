package com.skyblue.coalapp.server.lifeService.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "life_service_info")
@Setter
@Getter
public class LifeServiceInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private LifeServiceProvider provider;

    @ManyToOne
    private ServiceType type;

    //原价
    @Column(nullable = false,length = 10)
    private BigDecimal price;

    //优惠价格
    @Column(length = 10)
    private BigDecimal price2;

    @Column(length = 1024)
    private String images;

    @Column(length = 2048)
    private String desc;

    @Column(length = 2048)
    private Integer averageGrade;

    @Column(nullable = false,length = 2)
    private Integer state;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;
}
