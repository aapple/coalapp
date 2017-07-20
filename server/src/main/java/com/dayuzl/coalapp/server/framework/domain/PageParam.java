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

    public Integer checkPageNumber() {
        return this.pageNumber != null?this.pageNumber : 0;
    }

    public Integer checkPageSize(){
        return this.pageSize != null?this.pageSize :10;
    }
}