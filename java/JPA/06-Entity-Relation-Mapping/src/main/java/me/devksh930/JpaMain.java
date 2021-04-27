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
            saveNN(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();


    }

//    private static void save(EntityManager em) {
//        Product productA = new Product();
//        productA.setId("productA");
//        productA.setName("상품A");
//        em.persist(productA);
//
//        Member member1 = new Member();
//        member1.setId("member1");
//        member1.setUsername("회원1");
//        member1.getProducts().add(productA);
//        em.persist(member1);
//
//    }

//    public static void find(EntityManager em) {
//        Member member = em.find(Member.class, "member1");
//        List<Product> products = member.getProducts();
//        for (Product product : products) {
//            System.out.println("product.name " + product.getName());
//        }
//    }

//    public static void findInverse(EntityManager em) { //객체그래프 역방향탐색
//        Product product = em.find(Product.class, "productA");
//        List<Member> members = product.g();
//        for (Member member : members) {
//            System.out.println("member = " + member.getUsername());
//        }

//    }

    public static void saveNN(EntityManager em) {
//        다대다를 1:N, N:1로 풀어쓴 엔티티 이용시
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        em.persist(member1);

        Product productA = new Product();
        productA.setId("proudctA");
        productA.setName("상품1");
        em.persist(productA);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);
    }


}