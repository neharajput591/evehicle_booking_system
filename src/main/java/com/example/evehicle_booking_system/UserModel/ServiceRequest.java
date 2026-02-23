package com.example.evehicle_booking_system.UserModel;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ServicingId;

    Long userId;
    Long vehicleId;
    String modelName;

    LocalDate servicingDate;
    LocalTime servicingTime;

    String servicingDesc;


}
