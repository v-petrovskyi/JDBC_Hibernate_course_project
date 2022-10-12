package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL) //todo можливо потрібно змінити
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_services",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Incident> incidents;

    public User() {
    }

    public User(String userName, String password, UserRole userRole, Profile profile) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.profile = profile;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append(", profile=").append(profile);
        sb.append(", services=").append(services);
        sb.append(", incidents=").append(incidents);
        sb.append('}');
        return sb.toString();
    }
}
