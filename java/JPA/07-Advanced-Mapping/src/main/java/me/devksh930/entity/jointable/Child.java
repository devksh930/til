package me.devksh930.entity.jointable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;
    private String name;
}
