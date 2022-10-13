package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "problem_description")
    private String problemDescription;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH}/*, fetch = FetchType.EAGER*/)
    @JoinColumn(name = "user_id")
    private User user;

    public Incident() {
    }

    public Incident(String serviceName, boolean isActive, String problemDescription) {
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.problemDescription = problemDescription;
    }

    public Incident(String serviceName, boolean isActive, String problemDescription, User user) {
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.problemDescription = problemDescription;
        this.user = user;
    }

}
