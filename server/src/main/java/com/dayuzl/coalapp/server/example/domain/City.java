package com.dayuzl.coalapp.server.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yaobin on 2017/4/28.
 */
@Entity
@Table(name = "city")
@Setter
@Getter
public class City {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int cityId;

    @Column(nullable = false)
    private String name;
}
