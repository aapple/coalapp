package com.skyblue.coalapp.server.user.vo;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by yao on 2017/5/23.
 */
@Setter
@Getter
public class UserVO extends User{

    private String verifyCode;

}
