package me.devksh930.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Seller extends BaseEntity {
    private String shopName;
}
