package me.bingbingpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam ===== " + findTeam.getName());

            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("m =========== " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            em.close();
        }


        emf.close();
    }
}
