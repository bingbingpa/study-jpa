package me.bingbingpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
//            System.out.println("findMember.id ===== " + findMember.getId());
//            System.out.println("findMember.name ===== " + findMember.getName());
//            em.persist(member);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println("member ==== " + member.getName());
//            }
            tx.commit();
        } catch (Exception e) {
            em.close();
        }


        emf.close();
    }
}
