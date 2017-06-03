package com.skyblue.coalapp.server.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 张杨 on 2017/6/1.
 */
@Entity
@Table(name = "product_price_his")
@Setter
@Getter
public class ProductPriceHis{

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Factory factory;

    @ManyToOne
    private ProductType productType;

    @Column(nullable = false,length = 10)
    private BigDecimal price;

    @Column(nullable = true,length = 10)
    private BigDecimal price2;

    @Column(length = 10)
    private BigDecimal priceDiff;

    @Column
    private String coal_fareliang; //发热量
    @Column
    private String coal_quanshuifen; // 全水分
    @Column
    private String coal_liufen; // 硫份
    @Column
    private String coal_huifafen; // 挥发份
    @Column
    private String coal_huifen; // 灰分
    @Column
    private String coal_gudingtan; // 固定碳
    @Column
    private String coke_hantan; // 含碳
    @Column
    private String coke_hanliu; // 含硫
    @Column
    private String coke_shuifen; // 水份
    @Column
    private String coke_huifafen; // 挥发份
    @Column
    private String coke_huifen; // 灰分

    @Column(nullable = false,length = 2)
    private Integer state;

    @Column(columnDefinition="TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="TIMESTAMP")
    private Date createdTime;
}
