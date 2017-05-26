package com.skyblue.coalapp.server.example.domain;

import lombok.Getter;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Getter
public enum CommonEnum {

    STATE_YES("Y",1,"在售"),
    STATE_NO("N",2,"停售");

    private String name;
    private int value;
    private String desc;

    private CommonEnum(String name, int value ,String desc){
        this.name = name;
        this.value = value;
        this.desc = desc;
    };

    public static CommonEnum getByValue(int value){
        for (CommonEnum ce : CommonEnum.values()) {
            if(ce.getValue() == value){
                return ce;
            }
        }
        return null;
    }

}
