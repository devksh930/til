package me.devksh930.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
//    private String name;
//    private int age;

    //근무기간
//    @Embedded
//    Period period;
    //집주소
    @Embedded
    Address homeAddress;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
//            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
//            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_ZIPCODE"))
//
//    })
//    Address companyAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<String>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<Address>();

//    @Embedded
//    PhoneNumber phoneNumber;
}
