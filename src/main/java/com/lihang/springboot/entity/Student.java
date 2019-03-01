package com.lihang.springboot.entity;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {
    private int id;
    private String name;
    private int age;
    private int classId;
    private String sex;
    // 学院
    private String Dept;
}
