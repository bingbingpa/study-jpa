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
		// EntitiyManagerFactory는 어플리케이션이 실행되는 시점에 딱 하나만 만들어져야 한다.(DB당 하나만!)
		// EntityManger는 쓰레드간에 공유하면 안되고 사용하고 버려야 한다. 
		// JPA의 모든 데이터의 변경은 트랜잭션 안에서 실행해야 한다.              
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			// Member member = new Member();
			// member.setId(2L);
			// member.setName("HelloB");
			// em.persist(member);
			// Member findMember = em.find(Member.class, 1L);
			// findMember.setName("HelloJPA");
			// JPA를 통해서 Entity를 가져오면 JPA의 관리 대상이 되고 transaction을 commit하는 시점에 확인 후 변경된 사항이 있으면 
			// 업데이트 쿼리를 만들어서 날린다. 
			// JPQL은 엔티티 객체를 대상으로 쿼리를 하고 SQL은 데이터베이스 테이블을 대상으로 쿼리를 한다.
			List<Member> result = em.createQuery("selec		t m from Member as m", Member.class)
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
