package com.skyblue.coalapp.server.Information.domain;

import com.skyblue.coalapp.server.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 信息部
 */

@Entity
@Table(name = "info_department")
@Getter
@Setter
public class InfoDepartment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 128)
    private String title;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length = 1024)
    private String introduction;

    @Column
    private String callNumber;

    @Column(length = 128)
    private String point;

    @Column(nullable = false,length = 128)
    private String address;

    @Column(length = 256)
    private String photoPath;
}
