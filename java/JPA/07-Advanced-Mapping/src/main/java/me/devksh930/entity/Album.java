package me.devksh930.entity;

import lombok.Getter;
import lombok.Setter;
import me.devksh930.entity.supertype.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album extends Item {
    private String artist;

}
