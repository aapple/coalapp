package com.dayuzl.coalapp.server.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeFormat {

    private Integer id;

    private String name;

    private List<ProductTypeFormat> subList;

    public ProductTypeFormat(Integer id, String name){
        this.id = id;
        this.name = name;
    }

}
