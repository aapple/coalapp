package com.skyblue.coalapp.server.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Entity
@Table(name = "product_type")
@Getter
@Setter
public class ProductType {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private Integer factoryType;

//    // 1: 煤炭
//    COAL(1,"0000","coal"),
//    COAL_2_5(1,"1001","2-5籽"),
//    COAL_3_8(1,"1002","3-8籽"),
//    COAL_4_9(1,"1003","4-9籽"),
//    COAL_MIAN_MEI(1,"1004","面煤"),
//    COAL_YUAN_MEI(1,"1005","原煤"),
//    COAL_XIAO_KUAI(1,"1007","小块"),
//    COAL_ZHONG_KUAI(1,"1008","中块"),
//    COAL_DA_KUAI(1,"1009","大块"),
//
//    // 2: 兰炭
//    COKE(2,"0000","Coke"),
//    COKE_DA_LIAO(2,"2001","大料"),
//    COKE_ZHONG_LIAO(2,"2002","中料"),
//    COKE_XIAO_LIAO(2,"2003","小料"),
//    COKE_JIAO_MIAN(2,"2004","焦面");
}
