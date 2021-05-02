package me.devksh930.entity.compositeKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(ChildId.class)
@Getter
@Setter
public class Child {

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    @Id
    @Column(name = "CHILD_ID")
    private String childId;

    private String name;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
//            @JoinColumn(name = "PARENT_ID2",referencedColumnName = "PARENT_ID2")
//    })
//    private Parent parent;
}
