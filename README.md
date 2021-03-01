# [자바 ORM 표준 JPA 프로그래밍 - 기본편](https://www.inflearn.com/course/ORM-JPA-Basic)

### JPA(Java Persistence API) 소개
- 자바 진영의 **ORM** 기술 표준
- ORM
    - Object-relational mapping(객체 관계 매핑)
    - 객체는 객체대로 설계, 관계형 데이터베이스는 관계형 데이터베이스대로 설계
    - ORM 프레임워크가 중간에서 매핑
- JPA 는 인터페이스의 모음
- JPA 표준 명세를 구현한 구현한 3가지 구현제중 하나가 **하이버네이트**이다.
- JPA 를 왜 사용해야 하는가?
    - SQL 중심적인 개발에서 객체 중심으로 개발
    - 생산성
    - 유지보수 등등등...
    
### JPA 시작하기
- JPA 설정파일은 /META-INF/persistence.xml 
    - hibernate.dialect: 어떤 데이터베이스 인지 지정. ProgressDialect, H2Dialect, MySQL5InnoDBDialect 등등... 40가지 이상의 데이터베이스 방언 지원
    - javax 로 시작하는 옵션들은 자바 표준이고 hibernate 로 시작하는 것들은 hibernate 에서만 쓰이는 옵션들이다. 다른 orm 구현체를 쓸 경우 javax 옵션이 아닌 경우는 바뀔 수 있다.
    - hibernate.show_sql: 쿼리를 보여준다.
    - hibernate.format_sql: 쿼리를 정렬해서 보여준다.
    - hibernate.use_sql_comments: 어떤 쿼리인지 코멘트를 보여준다.
- 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
- **엔티티 매니저는 쓰레드간에 절대로 공유하면 안된다.**
- **JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.**
- JPQL: SQL 을 추상화한 **객체 지향 쿼리 언어**

### 영속성 관리 - 내부 동작 방식
- 영속성 컨텍스트
    - JPA 를 이해하는데 가장 중요한 용어
    - **엔티티를 영구 저장하는 환경**이라는 뜻
    - 영속성 컨텍스트는 논리적인 개념
    - 엔티티 매니저를 통해서 영속성 컨텍스트에 접근
- 엔티티의 생명주기
    - 비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 **새로운** 상태
      ~~~ java
      // 객체를 생성한 상태(비영속)
      Member member = new Member();
      member.setId("member1");
      member.setUsername("회원1");
      ~~~
    - 영속(managed): 영속성 컨텍스트에 **관리**되는 상태
      ~~~ java
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      // 객체를 저장한 상태(영속)
      em.persist(member);
      ~~~
    - 준영속(detached): 영속성 컨텍스트에 저장되었다가 **분리**된 상태
      ~~~ java
      // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
      em.detach(member);
      ~~~
    - 삭제(removed): **삭제**된 상태
      ~~~ java
      // 객체를 삭제한 상태
      em.remove(member);
      ~~~
- 영속성 컨텍스트의 이점
    - 1차 캐시
    - 동일성 보장
    - 트랜잭션을 지원하는 쓰기 지연
    - 변경 감지
    - 지연 로딩
- 플러시: 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
- 영속석 컨텍스트를 플러시하는 방법
    - em.flush() - 직접 호출 
    - 트랜잭션 커밋 - 플러시 자동 호출
    - JPQL 쿼리 실행 - 플러시 자동 호출
    
### 엔티티 매핑
- @Entity
    - **기본 생성자 필수** 
    - final 클래스, enum, interface, inner 클래스 사용 X
    - 저장할 필드에 final 사용 X
- hibernate.hbm2ddl.auto
    - create: 기존 테이블 삭제 후 다시 생성(DROP + CREATE)
    - create-drop: creat 와 같으나 종료시점에 테이블 drop
    - update: 변경분만 반영(**운영 DB 에는 사용하면 안됨**)
    - validate: 엔티티와 테이블이 정상 매핑 되었는지만 확인 
    - none: 사용하지 않음
- 매핑 어노테이션 정리
    - @Column: 컬럼 매핑
    - @Temporal: 날짜 타입 매핑
    - @Enumerated: enum 타입 매핑. default 는 ordinal value. **EnumType.STRING** 사용하면 enum 값이 그대로 저장. **ordinal 은 사용하지 말자**
    - @Lob: BLOB, CLOB 매핑
    - @Transient: 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
- 기본 키 매핑 방법
    - 직접 할당: @Id 만 사용
    - 자동생성(@GeneratedValue)
      - IDENTITY: 데이터베이스에 위임, MYSQL
        - AUTO_ INCREMENT 는 데이터베이스에 INSERT SQL 을 실행한 이후에 ID 값을 알 수 있음
        - IDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행 하고 DB 에서 식별자를 조회
      - SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, 각 테이블 마다 다른 시퀀스를 사용하려면 @SequenceGenerator 필요
        - allocationSize: 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용됨). 기본값 50. sequence call 을 size 를 다 사용하고 나면 하게 된다.
        하지만 was 를 내리면 그만큼 시퀀스에 구멍이 생기게 된다. 
        - **데이터베이스 시퀀스 값이 하나씩 증가하도록 설정되어 있으면 이 값을 반드시 1로 설정해야 한다.**
      - TABLE: 키 생성용 테이블 사용, 모든 DB 에서 사용. @TableGenerator 필요. 단점으로는 성능이 좀 떨어질 수 있다.
      - AUTO: 방언에 따라 자동 지정, 기본값
- 권장하는 식별자 전략
  - 기본 키 제약 조건: null 아님, 유일, 변하면 안된다.
  - 비즈니스를 키로 사용하지 마라. 예를 들어 주민등록 번호
  - 권장: Long 타입 + 대체키 + 키 생성전략 사용
  
### 연관관계 매핑 기초