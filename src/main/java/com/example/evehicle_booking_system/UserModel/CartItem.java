package com.example.evehicle_booking_system.UserModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cartItemId;
    // String modelname;
    String description;
    String modelname;
    String image;

    Double price = 0.0;

    // long userId;

    // Foreign key relationship: Many CartItems -> One User
    // @ManyToOne
    // @JoinColumn(name = "user_id", referencedColumnName = "userId")
    // private User user;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId")
    private Vehicle vehicle;
}
