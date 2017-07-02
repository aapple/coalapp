package com.dayuzl.coalapp.server.user.domain;

import lombok.Getter;

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
