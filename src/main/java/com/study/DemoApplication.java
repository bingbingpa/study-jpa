package com.study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.study.hellojpa.Member;

// @SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
		//EntitiyManagerFactory는 어플리케이션이 실행되는 시점에 딱 하나만 만들어져야 한다. 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Member member = new Member();

		member.setId(1L);
		member.setName("HelloA");
		em.persist(member);

		tx.commit();

		em.close();
		emf.close();
	}

}
