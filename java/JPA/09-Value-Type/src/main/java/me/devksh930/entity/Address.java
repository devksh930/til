package me.devksh930.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name = "city")
    public String city;
    private String street;
    private String zipcode;
}
