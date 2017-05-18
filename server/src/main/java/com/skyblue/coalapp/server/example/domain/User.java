package com.skyblue.coalapp.server.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by bin.yao on 2017/4/21.
 */
@Entity
@Table(name = "User")
@Setter
@Getter
public class User {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String userCode;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private Integer role;
    @Column(nullable = false)
    private String roleDesc;
    @Column(nullable = false)
    private Timestamp createTime;

    public User(){}

    public User(String phoneNum){
        this.userCode = phoneNum;
        this.userName = creatRandomName();
        this.phoneNum = phoneNum;
        this.role = new Integer(RoleEnum.ROLE_CUSTOMER.getValue());
        this.roleDesc = RoleEnum.ROLE_CUSTOMER.getDesc();
        this.createTime = Timestamp.valueOf(getCurrentTime());
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
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);

        return dateNowStr;
    }

}
