package com.study.hellojpa;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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

    /**
     * @Column : 특정 칼럼과 매핑 할 때
     * nullable = false로 설정할 경우 not null 제약조건이 붙는다.
     * unique 옵션은 pk명이 이상하게 생성되므로 되도록 사용하지 않는다.
     * columnDefinition : 컬럼에 대해 직접 정의 예) varchar(100) default 'EMPTY'
     * 숫자타입일 경우 precision, scale로 자릿수등을 설정 가능 
     */
    @Column(name="name", nullable = false)
    private String username;

    private Integer age;

    /**
     * @Enumerate : enum 타입 매핑
     * EnumType.ORDINAL은 사용하지 않는다(enum순서를 DB에 저장하기 때문에 enum이 바뀔 경우 순서가 바껴 버린다.)
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * @temporal : 날짜 타입 매핑 
     * LocalDate, LocalDateTime 타입을 쓸 경우 생략 가능(자바8 이상)
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date ceatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * @Lob : blob, clob 매핑  
     * 어노테이션을 주고 타입을 String으로 하면 문자타입의 칼럼이 생성된다.
     */
    @Lob
    private String description;

    /**
     * @Transient : DB와 관계없이 메모리상에서만 사용할 경우 
     */
    @Transient
    private int temp;
}