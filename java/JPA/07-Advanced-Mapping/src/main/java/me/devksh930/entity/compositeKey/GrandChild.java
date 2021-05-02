package me.devksh930.entity.compositeKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GrandChild {
    @Id
    @GeneratedValue
    @Column(name = "GRADCHILD_ID")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Child child;
}
