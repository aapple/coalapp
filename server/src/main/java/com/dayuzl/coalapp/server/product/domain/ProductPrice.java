package com.dayuzl.coalapp.server.product.domain;

import com.alibaba.fastjson.JSON;
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

    // 1.厂商 2.代发
    @Column(nullable = false)
    private Integer priceOwnerType;

    //1.一票 2.两票
    @Column
    private Integer priceType;

    @Column(nullable = false,length = 10)
    private BigDecimal price;

    @Column(nullable = true,length = 10)
    private BigDecimal price2;

    @Column(length = 10)
    private BigDecimal priceDiff;

    //产品图像地址
    @Column(length = 256)
    private String productImage;

    //化验报告图像地址
    @Column(length = 256)
    private String reportImage;

    //是否水洗 0:不是 1 是
    @Column
    private Integer coalWashing;

    //是否过筛  0:不是 1 是
    @Column
    private Integer graded;

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

    //1.在产 0.停产
    @Column(nullable = false,length = 2)
    private Integer state;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;

    public ProductPriceHis toProductPriceHis(){

        ProductPriceHis productPriceHis = new ProductPriceHis();

        productPriceHis.setFactory(this.factory);
        productPriceHis.setPriceOwnerType(this.priceOwnerType);
        productPriceHis.setPriceType(this.priceType);
        productPriceHis.setProductType(this.productType);
        productPriceHis.setPrice(this.price);
        productPriceHis.setPrice2(this.price2);
        productPriceHis.setCoalWashing(this.coalWashing);
        productPriceHis.setGraded(this.graded);
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
        productPriceHis.setProductImage(this.productImage);
        productPriceHis.setReportImage(this.reportImage);
        productPriceHis.setState(this.state);
        productPriceHis.setUpdateTime(this.getUpdateTime());
        productPriceHis.setCreatedTime(this.createdTime);

        return productPriceHis;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
