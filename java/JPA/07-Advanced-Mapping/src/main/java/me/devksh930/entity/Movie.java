package me.devksh930.entity;

import lombok.Getter;
import lombok.Setter;
import me.devksh930.entity.supertype.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
