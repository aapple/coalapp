package com.skyblue.coalapp.server.CoalIndustry.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by 张杨 on 2017/5/25.
 */
@Getter
@Setter
public class ProductVO {

    private String checkType;

    private String productName;
    private Integer productType;
    private String productCode;
    private BigDecimal prodPrice;
    private BigDecimal prodPrice2;
    private Integer  heatQuantity;
    private String state;
    private String factoryCode;
    private Timestamp updateTime;

}
