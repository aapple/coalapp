package com.skyblue.coalapp.server.CoalIndustry.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
@Entity
@Table(name = "factory")
@Getter
@Setter
public class Factory {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String factoryDescribe;

    @Column(length = 2)
    private Integer type;

    @Column(length = 2)
    private Integer state;

    @Column(nullable = false,length = 15)
    private String ownerCode;

    @ManyToMany
    private List<ProductPrice> products;
}
