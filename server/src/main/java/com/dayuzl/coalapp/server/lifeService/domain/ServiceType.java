package com.dayuzl.coalapp.server.lifeService.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service_type")
@Getter
@Setter
public class ServiceType {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 2)
    private Integer value;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
