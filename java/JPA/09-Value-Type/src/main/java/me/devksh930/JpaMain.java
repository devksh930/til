package me.devksh930;

import me.devksh930.entity.Address;
import me.devksh930.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            collectionValueSave(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void collectionValueSave(EntityManager em) {
        Member member = new Member();

        //임베디드 값타입
        member.setHomeAddress(new Address("부산","북구","12345"));

        //기본값 타입 컬렉션
        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        //임베디드 값 타입 컬렋녀
        member.getAddressHistory().add(new Address("부산", "해운대구", "612768"));
        member.getAddressHistory().add(new Address("부산", "사상구", "612768"));

        em.persist(member);
    }
}