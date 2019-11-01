package com.bingbingpa.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member { 
    
    @Id @GeneratedValue
    private Long id;
    
    @Column(name="member_id")
    private String name;
    
    private String city;
    
    private String street;
    
    private String zipcode;
}