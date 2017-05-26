package com.skyblue.coalapp.server.user.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yao on 2017/5/23.
 */
@Setter
@Getter
public class UserVO {

    private Integer id;
    private String phoneNum;
    private String verifyCode;
    private String nickname;
    private String avatar;
    private String bio;
    private Integer gender;
}
