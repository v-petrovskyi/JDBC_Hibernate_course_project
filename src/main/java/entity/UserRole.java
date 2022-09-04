package entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", unique = true)
    private Role role;

    @Column(name = "role_description")
    private String roleDescription;

    public UserRole() {
    }

    public UserRole(Role role, String roleDescription) {
        this.role = role;
        this.roleDescription = roleDescription;
    }


    public enum Role {
        USER, ADMIN, SUPER_ADMIN
    }
}
