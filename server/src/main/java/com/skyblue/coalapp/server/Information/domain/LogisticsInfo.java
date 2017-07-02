package com.skyblue.coalapp.server.Information.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "logistic_info")
@Getter
@Setter
public class LogisticsInfo {

    @Id
    @GeneratedValue
    private Integer id;

    //出发地
    @Column(nullable = false,length = 255)
    private String departure;

    //目的地
    @Column(nullable = false,length = 255)
    private String destination ;

    //总数量
    @Column(length = 5)
    private Integer totalQty;

    //剩余数量
    @Column(length = 5)
    private Integer remainQty;

    //车数
    @Column(length = 5)
    private Integer carNumber;

    //装车费
    @Column(length = 10)
    private BigDecimal loadingCharge;

    //卸车费
    @Column(length = 10)
    private BigDecimal unloadingCharge;

    //运费
    @Column(length = 10)
    private BigDecimal freightCharge;

    //信息费
    @Column(length = 10)
    private BigDecimal messageCharge;

    //备注
    @Column(length = 1024)
    private String comment;

    @Column
    private String loadingDate;

    @Column
    private String unloadingDate;

    //更新时间
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name="InfoDepartment_id")
    private InfoDepartment infoDepartment;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
