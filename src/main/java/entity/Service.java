package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "service_month_price")
    private double serviceMonthPrice;

    @Column(name = "customer_id")
    private long customerId;

    @ManyToMany
    @JoinTable(name = "user_services",
               joinColumns = @JoinColumn(name = "service_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Service() {
    }

    public Service(String serviceName, boolean isActive, double serviceMonthPrice, long customerId, List<User> users) {
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.serviceMonthPrice = serviceMonthPrice;
        this.customerId = customerId;
        this.users = users;
    }
}
