package me.devksh930;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
