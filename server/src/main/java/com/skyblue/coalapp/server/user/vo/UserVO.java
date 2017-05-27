package com.skyblue.coalapp.server.user.vo;

import com.skyblue.coalapp.server.CoalIndustry.vo.FactoryVO;
import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    private List<FactoryVO> factories;

    public static UserVO eoToVo(User user,List<FactoryVO> factories){

        UserVO userVO = new UserVO();

        userVO.setId(user.getId());
        userVO.setPhoneNum(user.getPhoneNum());
        userVO.setNickname(user.getNickname());
        userVO.setAvatar(user.getAvatar());
        userVO.setBio(user.getBio());
        userVO.setGender(user.getGender());
        userVO.setFactories(factories);

        return  userVO;
    }
}
