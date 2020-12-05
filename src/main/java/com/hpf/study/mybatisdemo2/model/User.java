package com.hpf.study.mybatisdemo2.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private BigDecimal id;
    private String name;
    private  int age;
    private int sex;
}
