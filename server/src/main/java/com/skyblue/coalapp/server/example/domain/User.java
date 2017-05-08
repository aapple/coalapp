package com.skyblue.coalapp.server.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bin.yao on 2017/4/21.
 */
@Getter
@Setter
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
}
