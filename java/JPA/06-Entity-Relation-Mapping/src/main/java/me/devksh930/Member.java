package me.devksh930;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<OrderDetails> orderdetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(mappedBy = "member")
    private Locker locker;

    public void setTeam(Team team) {
        this.team = team;
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }

}

