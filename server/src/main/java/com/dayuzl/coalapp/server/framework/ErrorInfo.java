package com.dayuzl.coalapp.server.framework;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yaobin on 2017/4/28.
 */
@Setter
@Getter
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;
    private Integer code;
    private String message;
    private String url;
    private T data;
}