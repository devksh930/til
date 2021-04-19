package me.devksh930;

import javax.persistence.*;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
