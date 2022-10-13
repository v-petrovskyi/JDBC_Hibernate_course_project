package entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("id=").append(id);
        sb.append(", role=").append(role);
        sb.append(", roleDescription='").append(roleDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public enum Role {
        USER, ADMIN, SUPER_ADMIN
    }
}
