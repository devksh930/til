package me.devksh930;

import me.devksh930.entity.compositeKey.Parent;
import me.devksh930.entity.compositeKey.ParentId;

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
//            CompositeKeySave(em);
            tx.commit();
            CompositeKeyFind(em);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
    public static void CompositeKeySave(EntityManager em){//복합키를 사용하는 엔티티 저장
        Parent parent = new Parent();
        parent.setId1("myId1");
        parent.setId2("myId2");
        parent.setName("parentName");
        em.persist(parent);
    }

    public static void CompositeKeyFind(EntityManager em){ //복합키를 이용한 조회
        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent = em.find(Parent.class, parentId);
        System.out.println(parent.getName());
    }
}