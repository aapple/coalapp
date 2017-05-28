package com.skyblue.coalapp.server.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 这个对象主要用来表现当前厂商供应货品信息
 */
@Entity
@Table(name = "product_price")
@Setter
@Getter
public class ProductPrice {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer factoryId;

    @Column(nullable = false)
    private Integer factoryType;

    @Column(nullable = false)
    private String productTypeId;

    @Column(nullable = false,length = 10)
    private String productTypeName;

    @Column(nullable = false,length = 10)
    private BigDecimal price;

    @Column(nullable = true,length = 10)
    private BigDecimal price2;

    @Column(nullable = false,length = 10)
    private Integer  heatQuantity;

    @Column(nullable = false,length = 2)
    private Integer state;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;

}
