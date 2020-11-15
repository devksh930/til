package me.devksh930;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            객체 매핑
            testSave(em);
            biDirection(em);
            queryLoginJoin(em);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

    //데이터저장
    private static void testSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);


    }

    // jpql을 이용한 조회
    private static void queryLoginJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where " + "t.name=:teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();
        for (Member member : resultList) {
            System.out.println("[query] member.username= " + member.getUsername());
        }
    }

    //수정
    private static void updateRelation(EntityManager em) {

        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    //연관관계제거
    private static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    private static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers(); //(팀 -> 회원)
        for (Member member : members) {
            System.out.println("members.username = " + member.getUsername());
        }
    }

}


