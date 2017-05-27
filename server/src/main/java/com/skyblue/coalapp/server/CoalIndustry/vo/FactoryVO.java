package com.skyblue.coalapp.server.CoalIndustry.vo;

import com.skyblue.coalapp.server.CoalIndustry.domain.Factory;
import com.skyblue.coalapp.server.CoalIndustry.domain.ProductPrice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/23.
 */
@Getter
@Setter
public class FactoryVO {

    private String factoyCode;
    private String factoryName;
    private String factoryDesc;
    private String ownerCode;
    private String ownerName;
    private List<ProductPrice> products;


    public static FactoryVO eoToVo(Factory factory){

        FactoryVO factoryVO = new FactoryVO();
        if(factory != null){
            factoryVO.setFactoyCode(factory.getCode());
            factoryVO.setFactoryName(factory.getName());
            factoryVO.setFactoryDesc(factory.getFactoryDescribe());
            factoryVO.setOwnerCode(factory.getOwnerCode());
        }

        return factoryVO;
    }
}
