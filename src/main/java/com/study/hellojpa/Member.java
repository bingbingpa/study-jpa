package com.study.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @Entity은 클래스는 JPA가 관리한다.
 * JPA를 사용해서 테이블과 매핑할 클래스는 @Entity가 필수.
 * 기본 생성자는 필수 
 * final 클래스, enum, interface, inner 클래스 사용 X 
 * 저장할 필드에 final 사용 X
 * @Table은 엔티티와 매피할 테이블 지정
 */
@Entity
@Getter
@Setter
public class Member {

    //PK가 뭔지 알려주는 것.
    @Id
    private Long id;
    private String name;
}