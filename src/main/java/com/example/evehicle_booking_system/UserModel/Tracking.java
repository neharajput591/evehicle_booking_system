package com.example.evehicle_booking_system.UserModel;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long trackingId;

    // Long orderId;

    String currentStatus;
    String location;
    String courierName;
    String remarks;

    LocalDate UpdatedAt;

    @OneToOne
    @JoinColumn(name="order_idtrack" , referencedColumnName = "orderId")
    private Order ordertrack;
    
}
