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
