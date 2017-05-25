package com.skyblue.coalapp.server.example.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Getter
public enum ProductType {
    // 1: 煤炭
    COAL(1,"0000","coal"),
    COAL_2_5(1,"1001","2-5籽"),
    COAL_3_8(1,"1002","3-8籽"),
    COAL_4_9(1,"1003","4-9籽"),
    COAL_MIAN_MEI(1,"1004","面煤"),
    COAL_YUAN_MEI(1,"1005","原煤"),
    COAL_XIAO_KUAI(1,"1007","小块"),
    COAL_ZHONG_KUAI(1,"1008","中块"),
    COAL_DA_KUAI(1,"1009","大块"),

    // 2: 兰炭
    COKE(2,"0000","Coke"),
    COKE_DA_LIAO(2,"2001","大料"),
    COKE_ZHONG_LIAO(2,"2002","中料"),
    COKE_XIAO_LIAO(2,"2003","小料"),
    COKE_JIAO_MIAN(2,"2004","焦面");

    private Integer type;
    private String code;
    private String desc;

    private ProductType(Integer type, String code, String desc){
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    //获取一类产品
    public static List<ProductType> getThisTypeAllProdcutList(int type){

        ArrayList<ProductType> productList = new ArrayList<ProductType>();

        for (ProductType product : ProductType.values()) {
            if(product.getType() == type && !product.code.equals("0000")){
                productList.add(product);
            }
        }

        return productList;
    }

    public static Map<String,ProductType> getThisTypeAllProdcutMap(int type){

        Map<String,ProductType> productMap = new HashMap<String,ProductType>();

        for (ProductType product : ProductType.values()) {
            if(product.type == type && !product.code.equals("0000")){
                productMap.put(product.code,product);
            }
        }

        return productMap;
    }

    //获取某个产品根据Code
    public static ProductType getProductByCode(String code){

        for (ProductType product : ProductType.values()) {
            if(product.code.equals("code")){
                return product;
            }
        }

        return null;
    }
}
