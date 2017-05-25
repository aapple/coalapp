package com.skyblue.coalapp.server.CoalIndustry.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    @Column(nullable = false,length = 2)
    private Integer productType;

    @Column(nullable = false,length = 4)
    private String productCode;

    @Column(nullable = false,length = 10)
    private String productName;

    @Column(nullable = false,length = 10)
    private BigDecimal prodPrice;

    @Column(nullable = true,length = 10)
    private BigDecimal prodPrice2;

    @Column(nullable = false,length = 10)
    private Integer  heatQuantity;

    @Column(nullable = false,length = 2)
    private Integer state;

    @Column(nullable = false,length = 10)
    private String factoryCode;

    @Column(nullable = false,length = 30)
    private Timestamp updateTime;

    public ProductPrice(){};

    public ProductPrice(ProductType prodType){
        this.productType = prodType.getType();
        this.productCode = prodType.getCode();
        this.productName = prodType.getDesc();

        this.prodPrice = new BigDecimal(0);
        this.heatQuantity =  new Integer(0);

        this.updateTime = Timestamp.valueOf(getCurrentTime());
    }

    public ProductPrice(ProductType prodType,String factoryCode){
        this.productType = prodType.getType();
        this.productCode = prodType.getCode();
        this.productName = prodType.getDesc();
        this.factoryCode = factoryCode;

        this.prodPrice = new BigDecimal(0);
        this.heatQuantity =  new Integer(0);

        this.updateTime = Timestamp.valueOf(getCurrentTime());
    }

    public ProductPrice(ProductType prodType, String factoryCode, BigDecimal prodPrice, Integer  heatQuantity){

        this.productType = prodType.getType();
        this.productCode = prodType.getCode();
        this.productName = prodType.getDesc();

        this.factoryCode = factoryCode;
        this.prodPrice = prodPrice;
        this.heatQuantity = heatQuantity;

        this.updateTime = Timestamp.valueOf(getCurrentTime());
    }

    public ProductPrice(ProductType prodType,String factoryCode, BigDecimal prodPrice,BigDecimal prodPrice2, Integer  heatQuantity){
        this.productType = prodType.getType();
        this.productCode = prodType.getCode();
        this.productName = prodType.getDesc();
        this.factoryCode = factoryCode;

        this.prodPrice = prodPrice;
        this.prodPrice2 = prodPrice2;
        this.heatQuantity = heatQuantity;

        this.updateTime = Timestamp.valueOf(getCurrentTime());
    }

    private String getCurrentTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);

        return dateNowStr;
    }
}
