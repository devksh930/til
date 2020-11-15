package me.devksh930;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;

    @Enumerated(EnumType.STRING)
    private String status;
}
