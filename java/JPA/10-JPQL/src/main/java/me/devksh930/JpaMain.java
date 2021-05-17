package me.devksh930;


import com.querydsl.jpa.impl.JPAQuery;
import me.devksh930.entity.Member;
import me.devksh930.entity.Team;
import org.hibernate.Criteria;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            init(em);
            tx.commit();
//            typeQuery(em);
//            query(em);
            postionalParameter(em);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void init(EntityManager em) {
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setAge(20);
        member.setUsername("kim");
        member.setTeam(team);
        em.persist(member);


    }

//    public static void findJpql(EntityManager em) {
//        String jpql = "select m from Member as m where m.username = 'kim' ";
//        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
//        for (Member member : resultList) {
//            System.out.println(member.getUsername());
//        }
//    }

    //    public static void findCriteria(EntityManager em) {
//        //Criteria 사용준비
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//        //루트 클래스(조회를 시작할 클래스)
//        Root<Member> m = query.from(Member.class);
//
//        //쿼리생성
//        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
//        List<Member> resultList = em.createQuery(cq).getResultList();
//        for (Member member : resultList) {
//            System.out.println(member.getUsername());
//        }
//
//    }
    public static void typeQuery(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m from Member m", Member.class);

        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    public static void query(EntityManager em) {
        Query query = em.createQuery("SELECT m.username, m.age from Member m");

        List resultList = query.getResultList();

        for (Object o : resultList) {
            Object[] result = (Object[]) o;
            System.out.println("username " + result[0]);
            System.out.println("age " + result[1]);

        }
    }

    public static void namedParameter(EntityManager em) {
        String usernameParam = "kim";

//        TypedQuery<Member> query = em.createQuery("SELECT  m FROM Member m WHERE m.username =:username", Member.class);
//
//        query.setParameter("username", usernameParam);
//        List<Member> resultList = query.getResultList();

//        메서드 체이닝 방식
        List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE m.username =:username", Member.class).setParameter("username", usernameParam).getResultList();
        for (Member member : resultList) {
            System.out.println(member.getId());
            System.out.println(member.getUsername());
        }
    }

    public static void postionalParameter(EntityManager em) {
        String usernameParam = "kim";

        List<Member> resultList = em.createQuery("SELECT m FROM Member m where m.username =?1", Member.class)
                .setParameter(1, usernameParam)
                .getResultList();

        for (Member member : resultList) {
            System.out.println(member.getId());
            System.out.println(member.getUsername());
        }
    }


}