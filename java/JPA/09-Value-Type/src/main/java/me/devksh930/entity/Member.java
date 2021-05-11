package me.devksh930.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    //근무기간
    @Embedded
    Period period;
    //집주소
    @Embedded
    Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_ZIPCODE"))

    })
    Address companyAddress;

    @Embedded
    PhoneNumber phoneNumber;
}
