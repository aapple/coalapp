package com.dayuzl.coalapp.server.trafficinfo.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "traffic_info")
@Getter
@Setter
public class TrafficInfo {

    @Id
    @GeneratedValue
    private Integer id;

    //起点
    @Column(nullable = false)
    private String departure;

    //终点
    private String destination ;

    //状态
    @Column(nullable = false)
    private String state;

    @Column
    private String trafficTime;

    @Column
    private String area ;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public String toString(){
        String messsge = this.departure +" 至 "+ this.destination +"路段"+this.state +"  "+this.updateTime;
        return JSON.toJSONString(this);
    }
}
