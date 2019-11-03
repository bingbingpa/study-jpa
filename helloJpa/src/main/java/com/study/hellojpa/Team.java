package com.study.hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team { 

    @Id
    @GeneratedValue
    @Column(name="team_id")
    private Long id;
    
    private String name;
}