package me.devksh930.entity.compositeKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
//            @JoinColumn(name = "PARENT_ID2",referencedColumnName = "PARENT_ID2")
//    })
//    private Parent parent;
}
