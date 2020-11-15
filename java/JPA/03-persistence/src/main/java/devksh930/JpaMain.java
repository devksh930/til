package devksh930;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em) {
        Member member = new Member();
        member.setId("member1");
        member.setUsername("회원1");
        member.setAge(20);

        em.persist(member);

        Member findmember = em.find(Member.class, "member1");
        System.out.println(findmember.getUsername());

        Member findmember2 = em.find(Member.class, "member2");
        System.out.println(findmember2.getUsername());

        Member a = em.find(Member.class, "memeber2");
        Member b = em.find(Member.class, "memeber2");
        System.out.println("동일성 비교");
        System.out.println(a == b);

        em.remove(member);
    }
}
