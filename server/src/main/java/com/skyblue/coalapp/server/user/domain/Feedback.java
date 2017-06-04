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
@Table(name = "feedback")
@Setter
@Getter
public class Feedback {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 10000)
    private String feedbackText;

    @ManyToOne
    private User user;

    @Column
    private String phoneNum;

    @Column
    private String userName;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;

    @Column(columnDefinition="datetime")
    private Date created_at;

}
