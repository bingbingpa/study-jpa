package com.study.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    //PK가 뭔지 알려주는 것.
    @Id
    private Long id;
    private String name;
}