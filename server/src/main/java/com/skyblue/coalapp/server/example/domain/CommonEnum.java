package com.skyblue.coalapp.server.example.domain;

import lombok.Getter;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Getter
public enum CommonEnum {

    STATE_YES("Y",1),
    STATE_NO("N",2);

    private String name;
    private int value;

    private CommonEnum(String name, int value){};
}
