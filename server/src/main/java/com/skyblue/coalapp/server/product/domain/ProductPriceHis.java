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

    @Column(nullable = false,length = 10)
    private Integer  heatQuantity;

    @Column(nullable = false,length = 2)
    private Integer state;

    @Column(columnDefinition="TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="TIMESTAMP")
    private Date createdTime;
}
