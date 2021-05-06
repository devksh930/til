package me.devksh930;

import me.devksh930.entity.Member;
import me.devksh930.entity.Team;

import javax.persistence.*;
import java.util.Arrays;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            teamSave(em);
            tx.commit();
            ProxyTest(em);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void teamSave(EntityManager em) {
        Team team1 = new Team();
        team1.setName("팀1");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("팀2");
        em.persist(team2);

        Member member1 = new Member();
        member1.setUsername("회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("회원2");
        member2.setTeam(team2);
        em.persist(member2);
    }

    public static void printUserAndTeam(EntityManager em) {
        Member member = em.find(Member.class, "회원1");
        Team team = member.getTeam();
        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("소속팀 : " + team.getName());

    }

    public static void printUser(EntityManager em) {
        Member member = em.find(Member.class, "회원1");
        System.out.println("회원 이름: " + member.getUsername());
    }

    public static void ProxyTest(EntityManager em) {
        Member member = em.find(Member.class, "회원1");
        Team team = member.getTeam();
    }
}