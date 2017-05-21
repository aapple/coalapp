package com.skyblue.coalapp.server.repsonse;

/**
 * Created by yao on 2017/5/21.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }
}
