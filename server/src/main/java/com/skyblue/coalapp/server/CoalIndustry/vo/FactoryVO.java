package com.skyblue.coalapp.server.CoalIndustry.vo;

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

    private String facotruName;

    private List<ProductPrice> products;
}
