package com.example.evehicle_booking_system.UserModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long paymentId;

    // Long orderId;

    String modelname;
    String image; 
    Double price;
    Long vehicleId;

    // String paymentMethod;
    // String transactionId;

    // String paymentStatus;

    // LocalDate paymenDate;

    // Double amount; 

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order orderidpay;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id")
    private User userpayment ;
    
}
