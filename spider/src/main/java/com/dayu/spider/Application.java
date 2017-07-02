package com.dayu.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2017/4/12.
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.geccocrawler.*","com.young.*"})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
