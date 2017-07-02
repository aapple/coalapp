package com.dayuzl.coalapp.server.product.domain;

import com.alibaba.fastjson.JSON;
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

    public String toString(){
        return JSON.toJSONString(this);
    }
}
