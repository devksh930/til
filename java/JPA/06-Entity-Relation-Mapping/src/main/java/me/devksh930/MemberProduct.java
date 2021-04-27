package me.devksh930;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(MemberProductId.class)
@Getter
@Setter
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;
}
