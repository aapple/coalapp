package com.dayuzl.coalapp.server.user.service;


import com.dayuzl.coalapp.server.user.domain.User;

/**
 * Created by 张杨 on 2017/5/16.
 */
public interface LoginService {

    /*
     *  根据手机号获取验证码
     */
    public String getVerificationCode(String phoneNum);

    /*
     *  登陆
     */
    public User login(String phoneNum);
}
