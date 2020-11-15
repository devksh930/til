package devksh930;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMerge {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", "회원1", 4);

        member.setUsername("회원명변경");

        mergeMember(member);
        emf.close();
    }

    private static Member createMember(String id, String username, int age) {
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);

        em1.persist(member);
        tx1.commit();

        em1.close();// 영속 컨텍스트 1 종료
        // member 엔티티 준영속상태

        return member;
    }

    private static void mergeMember(Member member) {
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        //준영속 상태
        System.out.println("member = " + member.getUsername());
        //영속상태
        System.out.println("mergemeber " + mergeMember.getUsername());
        System.out.println("em2 contains member = " + em2.contains(member));
        System.out.printf("em2 contains mergemeber" + em2.contains(mergeMember));

        em2.close();

    }
}
