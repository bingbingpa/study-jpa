package com.bingbingpa.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		/**
		 * 데이터 중심 설계의 문제점
		 * 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
		 * 테이블의 외래키를 객체에 그대로 가져옴 
		 * 객체 그래프 탐색이 불가능 
		 * 참조가 없으므로 UML도 잘못됨
		 */
		// SpringApplication.run(DemoApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

}
