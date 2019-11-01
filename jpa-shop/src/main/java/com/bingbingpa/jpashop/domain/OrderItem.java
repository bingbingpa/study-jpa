package com.bingbingpa.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @Column(name="order_id")
    private String orderId;

    @Column(name="item_id")
    private Long itemId;

    private int orderPrice;

    private int count;
}