package com.example.evehicle_booking_system.UserModel;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long vehicleId;
    
    String modelName;
    String brand;
    String type;
    String description;

    Double price =0.0;

    Integer rangeKm =0;
    Integer batteryCapacity =0;
    Integer power =0;
    Integer stock =0;

      @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    
    // @Transient
    // private String base64Image;

    @Column(length = 500)
    private String imageUrl;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<CartItem> cartItems12;

    @OneToMany(mappedBy = "vehicleorder" , cascade = CascadeType.ALL)
    private List<Order> order12;

    @OneToMany(mappedBy = "vehiclefeed", cascade = CascadeType.ALL)
    private List<Feedback> vehiclefeedback;
    
    
}
