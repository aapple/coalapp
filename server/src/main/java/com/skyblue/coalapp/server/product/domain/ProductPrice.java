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

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;

    public ProductPriceHis toProductPriceHis(){

        ProductPriceHis productPriceHis = new ProductPriceHis();

        productPriceHis.setFactory(this.factory);
        productPriceHis.setProductType(this.productType);
        productPriceHis.setPrice(this.price);
        productPriceHis.setPrice2(this.price2);
        productPriceHis.setPriceDiff(this.priceDiff);
        productPriceHis.setCoal_fareliang(this.coal_fareliang);
        productPriceHis.setCoal_gudingtan(this.coal_gudingtan);
        productPriceHis.setCoal_huifafen(this.coal_huifafen);
        productPriceHis.setCoal_huifen(this.coal_huifen);
        productPriceHis.setCoal_quanshuifen(this.coal_quanshuifen);
        productPriceHis.setCoal_liufen(this.coal_liufen);
        productPriceHis.setCoke_hanliu(this.coke_hanliu);
        productPriceHis.setCoke_hantan(this.coke_hantan);
        productPriceHis.setCoke_huifafen(this.coke_huifafen);
        productPriceHis.setCoke_huifen(this.coke_huifen);
        productPriceHis.setCoke_shuifen(this.coke_shuifen);
        productPriceHis.setState(this.state);
        productPriceHis.setUpdateTime(this.getUpdateTime());
        productPriceHis.setCreatedTime(this.createdTime);

        return productPriceHis;
    }
}
