package com.dayuzl.coalapp.server.product.domain;

import com.alibaba.fastjson.JSON;
import com.dayuzl.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @Column (length = 10000)
    private String factoryDescribe;

    @Column(length = 2)
    private Integer factoryType;

    @Column(length = 2)
    private Integer state;

    //厂矿图片
    @Column(length = 255)
    private String factoryImage;

    @Column
    private String callPerson;

    @Column
    private String callNumber;

    @ManyToOne
    private User onwer;

    @ManyToOne
    private User saler;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(columnDefinition="datetime")
    private Date createdTime;

    public String toString(){
        return JSON.toJSONString(this);
    }
}
