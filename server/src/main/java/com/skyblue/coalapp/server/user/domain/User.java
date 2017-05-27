package com.skyblue.coalapp.server.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by bin.yao on 2017/4/21.
 */
@Entity
@Table(name = "user")
@Setter
@Getter
public class User {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 30)
    private String userName;

    @Column(nullable = false,unique = true,length = 20)
    private String phoneNum;

    @Column(nullable = false,length = 2)
    private Integer role;

    @Column
    private Integer isCoalManager = 0;

    @Column
    private Integer isCoalSaler = 0;

    @Column
    private Integer isCokeManager = 0;

    @Column
    private Integer isInfoStoreManager = 0;

    @Column
    private String nickname;

    @Column
    private String avatar;

    @Column
    private String bio;

    @Column
    private Integer gender;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="datetime")
    private Date created_at;

    public User(){}

    public User(String phoneNum){
        this.nickname = creatRandomName();
        this.phoneNum = phoneNum;
        this.role = new Integer(RoleEnum.ROLE_CUSTOMER.getValue());
        this.created_at = new Date();
    }

    private String creatRandomName() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    private String getCurrentTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);

        return dateNowStr;
    }

}
