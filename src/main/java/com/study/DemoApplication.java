package com.study;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.study.hellojpa.Member;

// @SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
		/**
		 * EntitiyManagerFactory는 어플리케이션이 실행되는 시점에 딱 하나만 만들어져야 한다.(DB당 하나만!)
		 * EntityManager는 고객의 요청이 올때마다 하나씩 생성이 된다. 
		 * EntityManger는 쓰레드간에 공유하면 안되고 사용하고 버려야 한다. 
		 * JPA의 모든 데이터의 변경은 트랜잭션 안에서 실행해야 한다.    
		 * JPA의 목적은 자바 컬렉션 다루듯이 객체를 다루는 것이다. (영속성 컨텍스트의 변경 감지)      
		 * 따로 업데이트 하지 않고 가져온 객체를 set해주면 커밋되는 시점에 업데이트 쿼리가 나간다.    
		 * em.flush를 호출하게 되면 쓰기 지연 SQL 저장소의 내용이 DB에 반영되지만 1차 캐시가 지워지는 것은 아니다. (em == EntityManager)
		 * 영속상태에서 DB에 바로 저장되지 않고 commit이 되는 순간 저장이 된다. 
		 * JPA를 통해서 Entity를 가져오면 JPA의 관리 대상이 되고 transaction을 commit하는 시점에 확인 후 변경된 사항이 있으면 업데이트 쿼리를 만들어서 날린다. 
		 * JPQL을 실행할 때는 flush가 자동으로 된다. 왜냐하면 영속석 컨텍스트에 insert를 한경우는 JPQL로 조회 할 수 없기 때문에 자동으로 flush하여 DB에 반영 후 JPQL을 실행한다.
		 * em.setFlushMode(FlushModeType.COMMIT) -> 커밋할 때만 플러시 
		 * 위 옵션은 JPQL을 실행할 때 flush가 필요 없는 경우 사용. 하지만 되도록 이면 AUTO로 사용하도록 하자!
		 * em.detach(entity) -> 준영속 상태로 만든다. 변경감지(dirty checking)가 되지 않는다. 즉 DB에서 하나를 조회해와서 영속성 컨텍스트에 올라간 상태에서 
		 * set을 통해 값을 변경후 em.detach(entity)를 하고 커밋을 해도 업데이트가 되지 않는다. 
		 */
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			//객체를 생성한 상태 (비영속))
			// Member member = new Member();
			// member.setId(2L);
			// member.setName("HelloB");

			// 엔티티를 영속성 컨텍스트라는 곳에 저장 (영속)
			// em.persist(member); 
			// Member findMember = em.find(Member.class, 1L);
			// findMember.setName("HelloJPA");

			// JPQL은 엔티티 객체를 대상으로 쿼리를 하고 SQL은 데이터베이스 테이블을 대상으로 쿼리를 한다.
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
				.setFirstResult(5)
				.setMaxResults(8)
				.getResultList();
			for(Member member : result) {
				System.out.println("member.name ======= " +member.getName());
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
