package com.skyblue.coalapp.server.example.domain;

import lombok.Getter;

/**
 * Created by 张杨 on 2017/5/18.
 */

@Getter
public enum RoleEnum {

    ROLE_SYS_MANAGER("manager",0),
    ROLE_BUSINESS_MANAGER("bizManager",1),
    ROLE_CUSTOMER("customer",2);


    private String desc;
    private Integer value;

    RoleEnum(String desc, Integer value){
        this.desc = desc;
        this.value = value;
    }
}
