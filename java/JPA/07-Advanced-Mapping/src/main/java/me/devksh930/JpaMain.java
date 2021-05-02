package me.devksh930;

import me.devksh930.entity.Board;
import me.devksh930.entity.BoardDetail;
import me.devksh930.entity.compositeKey.Parent;

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
            OneToOneSave(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    //    public static void CompositeKeySaveIdClass(EntityManager em){//복합키를 사용하는 엔티티 저장 - @IdClass이용
//        Parent parent = new Parent();
//        parent.setId1("myId1");
//        parent.setId2("myId2");
//        parent.setName("parentName");
//        em.persist(parent);
//    }
//
//    public static void CompositeKeyFind(EntityManager em){ //복합키를 이용한 조회 - @IdClass이용
//        ParentId parentId = new ParentId("myId1", "myId2");
//        Parent parent = em.find(Parent.class, parentId);
//        System.out.println(parent.getName());
//    }
//    public static void CompositeKeySaveEmbeddedId(EntityManager em) { //복합키를 사용하는 엔티티 저장 -@EmbeddedId 사용
//        Parent parent = new Parent();
//        ParentId parentId = new ParentId("myId1", "myId2");
//        parent.setId(parentId);
//        parent.setName("parentName");
//
//        em.persist(parent);
//
//    }
//
//    public static void CompositeKeyFindEmbeddedId(EntityManager em) { // 복합키를 이용한 조회 -@EmbeddedId 사용
//        ParentId parentId = new ParentId("myId1", "myId2");
//        Parent parent = em.find(Parent.class, parentId);
//        System.out.println(parent.getName());
//    }
//
//    public static void ParentId_equals_test() {
//        ParentId id1 = new ParentId();
//        id1.setId1("myId1");
//        id1.setId2("myId2");
//
//        ParentId id2 = new ParentId();
//        id2.setId1("myId1");
//        id2.setId2("myId2");
//
//        System.out.println(id1.equals(id2));
//    }
    public static void OneToOneSave(EntityManager em) {
        Board board = new Board();
        board.setTitle("제목");
        em.persist(board);

        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setContent("내용");
        boardDetail.setBoard(board);
        em.persist(boardDetail);
    }
}