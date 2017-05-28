package com.skyblue.coalapp.server.Information.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "logistic_info")
@Getter
@Setter
public class LogisticsInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,length = 255)
    private String departure;

    @Column(nullable = false,length = 255)
    private String destination ;

    @Column(length = 5)
    private Integer totalQty;

    @Column(length = 5)
    private Integer remainQty;

    @Column(length = 5)
    private Integer carNumber;

    @Column(length = 10)
    private BigDecimal loadingCharge;

    @Column(length = 10)
    private BigDecimal unloadingCharge;

    @Column(length = 10)
    private BigDecimal freightCharge;

    @Column(length = 10)
    private BigDecimal messageCharge;

    @Column
    private String loadingDate;

    @Column
    private String unloadingDate;

    @Column(columnDefinition="datetime")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name="InfoDepartment_id")
    private InfoDepartment infoDepartment;
}
