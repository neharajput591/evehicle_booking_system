package com.example.evehicle_booking_system.UserModel;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") // ✅ lowercase table name (avoid SQL conflicts)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") 
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true) // ✅ email should be unique
    private String email;

    @Column(nullable = false)
    private String password;

    private Long phone;
    private String address;
    private String city;
    private String role;

    private LocalDate createdAt;

    // Automatically set createdAt when saving for the first time
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDate.now();
    }

    // 🔗 Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "userorder", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "userfeedback", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

     @OneToMany(mappedBy = "userpayment", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
