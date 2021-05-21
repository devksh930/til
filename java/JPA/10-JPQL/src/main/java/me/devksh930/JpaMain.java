package me.devksh930;


import com.querydsl.jpa.impl.JPAQuery;
import me.devksh930.DTO.UserDTO;
import me.devksh930.entity.*;
import org.hibernate.Criteria;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            tx.commit();
            innerJoin(em);
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

        Member member1 = new Member();
        member1.setAge(20);
        member1.setUsername("kim");
        member1.setTeam(team);
        em.persist(member1);

        Member member2 = new Member();
        member2.setAge(20);
        member2.setUsername("hong");
        member2.setTeam(team);
        em.persist(member2);

        Member findmember = em.find(Member.class, 1L);
        Order order = new Order();
        order.setMember(findmember);
        order.setOrderAmount(5);
        order.setAddress(new Address("home", "busan", "12121"));
        order.setProduct(em.find(Product.class, 1L));
        em.persist(order);
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

    public static void manyValueFind(EntityManager em) {
        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            String username = (String) row[0];
            Integer age = (Integer) row[1];
            System.out.println("username= " + username + "age= " + age);

        }
//        Iterator iterator = resultList.iterator();
//        while (iterator.hasNext()) {
//            Object[] row = (Object[]) iterator.next();
//            String username = (String) row[0];
//            Integer age = (Integer) row[1];
//            System.out.println("username= "+ username+ "age= "+age);
//    }
    }

    public static void manyProjectionEntityFind(EntityManager em) { //여러 프로젝션 엔티티 타입으로 조회
        List<Object[]> resultList = em.createQuery("SELECT o.member, o.product, o.orderAmount FROM Order o").getResultList();

        for (Object[] row : resultList) {
            Member member = (Member) row[0];    // 엔티티
            Product product = (Product) row[1]; // 엔티티
            int orderAmount = (Integer) row[2]; // 스칼라

            System.out.println("member = " + member + " product = " + product + " orderAmount = " + orderAmount);
        }
    }

    public static List<UserDTO> mappingDtoFind(EntityManager em) { //DTO를 사용한 조회
//        List<Object[]> resultList = em.createQuery("SELECT m.username,m.age FROM Member m").getResultList();
//        //객체 변환
//        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
//        for (Object[] row : resultList) {
//            UserDTO userDTO = new UserDTO((String) row[0], (Integer) row[1]);
//            userDTOS.add(userDTO);
//        }
//
//        new 명령어 사용
        List<UserDTO> userDTOS = em.createQuery("SELECT new me.devksh930.DTO.UserDTO(m.username,m.age)FROM Member m", UserDTO.class).getResultList();
        return userDTOS;
    }

    public static void pagingAPI(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC ", Member.class);

        query.setFirstResult(0);
        query.setMaxResults(20);
        List<Member> resultList = query.getResultList();

        for (Member member : resultList) {
            System.out.println(member.getId());
        }
    }

    public static void innerJoin(EntityManager em) {
        String teamName = "팀1";
        String query = "SELECT m FROM Member m INNER JOIN m.team t" +
                " WHERE t.name = :teamName";
        List<Member> teamName1 = em.createQuery(query, Member.class).setParameter("teamName", teamName).getResultList();

        for (Member member : teamName1) {
            System.out.println(member.getUsername());
            System.out.println(member.getTeam().getName());
        }
    }

}