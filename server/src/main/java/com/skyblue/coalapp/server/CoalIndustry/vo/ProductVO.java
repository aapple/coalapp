package com.skyblue.coalapp.server.CoalIndustry.vo;

import com.skyblue.coalapp.server.CoalIndustry.domain.ProductPrice;
import com.skyblue.coalapp.server.example.domain.CommonEnum;
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
    private Integer state;
    private String factoryCode;
    private Timestamp updateTime;

    public static ProductVO  prodToProdVO(ProductPrice productPrice){

        ProductVO productVO = new ProductVO();

        if(null != productPrice){
            productVO.setProductName(productPrice.getProductName());
            productVO.setProductCode(productPrice.getProductCode());
            productVO.setProductType(productPrice.getProductType());
            productVO.setProdPrice(productPrice.getProdPrice());
            productVO.setProdPrice2(productPrice.getProdPrice2());
            productVO.setHeatQuantity(productPrice.getHeatQuantity());
            productVO.setState(productPrice.getState());
            productVO.setUpdateTime(productPrice.getUpdateTime());
        }

        return productVO;
    }

    public static ProductPrice prodVOToProd(ProductVO productVO){

        ProductPrice productPrice = new ProductPrice();

        productPrice.setProductName(productVO.getProductName());
        productPrice.setProductCode(productVO.getProductCode());
        productPrice.setProductType(productVO.getProductType());
        productPrice.setProdPrice(productVO.getProdPrice());
        productPrice.setProdPrice2(productVO.getProdPrice2());
        productPrice.setHeatQuantity(productVO.getHeatQuantity());
        productPrice.setState(productVO.getState());
        productPrice.setUpdateTime(productVO.getUpdateTime());

        return productPrice;
    }

}
