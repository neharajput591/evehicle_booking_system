package com.example.evehicle_booking_system.UserModel;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name="order12")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long orderId;
    
    // Long userId;
    // Long paymentId;
    // Long vehicleId;

    LocalDate orderDate;

    double totalAmount;
    String bookingStatus;

    @ManyToOne
    @JoinColumn(name = "user_id_ord" , referencedColumnName = "user_id")
    private User userorder ;

    @ManyToOne
    @JoinColumn(name = "vehicel_id_ord" , referencedColumnName = "vehicleId")
    private Vehicle vehicleorder ;

    @OneToOne(mappedBy = "orderidpay" ,cascade = CascadeType.ALL)
    private Payment payment12;


    @OneToOne(mappedBy = "ordertrack" ,cascade = CascadeType.ALL)
    private Tracking track;;
    
}
