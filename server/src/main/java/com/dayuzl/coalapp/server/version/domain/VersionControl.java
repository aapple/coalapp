package com.dayuzl.coalapp.server.version.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "version_control")
@Getter
@Setter
public class VersionControl {

    @Id
    @GeneratedValue
    private Integer id;

    //版本号
    @Column(nullable = false)
    private String versionNum ;

    //1.android 2.ios 3.windows
    @Column(nullable = false, length = 10)
    private String systemType;

    //是否强制更新
    @Column(nullable = false)
    private Boolean forceUpdate;

    //版本地址
    @Column(nullable = false)
    private String versionAddr;

    //版本新内容
    @Column(length = 2048)
    private String content;

    //发布时间
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    @Transient
    private Boolean isNeedUpdate;

    public String toString(){
        return JSON.toJSONString(this);
    }
}