package me.devksh930.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {
    public String city;
    private String street;
    @Embedded Zipcode zipcode;
}
