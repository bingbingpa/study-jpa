package me.bingbingpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
    private Long id;

    private String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
