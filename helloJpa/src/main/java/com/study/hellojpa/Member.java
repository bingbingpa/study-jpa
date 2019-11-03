package com.study.hellojpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
@SequenceGenerator(name="MEMER_SEQ_GENERATOR" ,
                   sequenceName = "MEMBER_SEQ", 
                   initialValue = 1, allocationSize = 10) //allocationSize만큼 메모리에 저장해두고 쓴다. 네트워크를 그만큼 덜 탄다. 50에서 100정도가 적정 
@Getter
@Setter
public class Member {

    /**
     * 권장하는 식별자 전략 
     * 기본 키 제약 조건: null 아님, 유일, 변하면 안된다.
     * 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자. 
     * 권장 : Long형 + 대체키 + 키 생성전략 사용(랜덤키)
     */

    /**
     * PK가 뭔지 알려주는 것.
     * GenerationType.AUTO : 키 자동 생성
     * GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 위임(버퍼링 불가)
     * GenerationType.SEQUENCE : 버퍼링 가능
     * SEQUENCE를 쓸 때는 데이터 타입을 Long을 쓰는게 좋다. 테이블에 건수가 얼마 안된다고 하면 Integer를 써도 될 것 같다. 
     * GenerationType.TABLE : 키생성 전용 테이블을 사용. 장점은 모든 DB에 사용 가능. 단점으로는 성능이 조금 떨어짐.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMER_SEQ_GENERATOR")
    @Column(name="member_id")
    private Long id;

    /**
     * @Column : 특정 칼럼과 매핑 할 때
     * nullable = false로 설정할 경우 not null 제약조건이 붙는다.
     * unique 옵션은 pk명이 이상하게 생성되므로 되도록 사용하지 않는다.
     * columnDefinition : 컬럼에 대해 직접 정의 예) varchar(100) default 'EMPTY'
     * 숫자타입일 경우 precision, scale로 자릿수등을 설정 가능 
     */
    @Column(name="username", nullable = false)
    private String username;

    // @Column(name="team_id")
    // private Long teamId;
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    // private Integer age;

    /**
     * @Enumerate : enum 타입 매핑
     * EnumType.ORDINAL은 사용하지 않는다(enum순서를 DB에 저장하기 때문에 enum이 바뀔 경우 순서가 바껴 버린다.)
     */
    // @Enumerated(EnumType.STRING)
    // private RoleType roleType;

    /**
     * @temporal : 날짜 타입 매핑 
     * LocalDate, LocalDateTime 타입을 쓸 경우 생략 가능(자바8 이상)
     */
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date ceatedDate;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date lastModifiedDate;

    /**
     * @Lob : blob, clob 매핑  
     * 어노테이션을 주고 타입을 String으로 하면 문자타입의 칼럼이 생성된다.
     */
    // @Lob
    // private String description;

    /**
     * @Transient : DB와 관계없이 메모리상에서만 사용할 경우 
     */
    @Transient
    private int temp;
}