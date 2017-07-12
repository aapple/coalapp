package com.dayuzl.coalapp.server.freight.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "freight_info")
@Getter
@Setter
public class FreightInfo {

    @Id
    @GeneratedValue
    private Integer id;

    //起点
    @Column(nullable = false)
    private String departure;

    //终点
    @Column(nullable = false)
    private String destination ;

    @Column
    private BigDecimal mixPrice;

    @Column
    private BigDecimal minPrice;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
