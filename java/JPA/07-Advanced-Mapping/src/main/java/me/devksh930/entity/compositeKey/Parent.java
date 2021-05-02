package me.devksh930.entity.compositeKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Parent {

    @Id
    @Column(name = "PARENT_ID")
    private ParentId id;

    private String name;
}
