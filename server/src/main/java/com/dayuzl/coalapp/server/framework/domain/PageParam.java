package com.dayuzl.coalapp.server.framework.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageParam {

    //页号
    Integer pageNumber;

    //每页容量
    Integer pageSize;
}
