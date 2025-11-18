package com.example.evehicle_booking_system.UserModel;

import java.time.LocalDate;

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
public class Feedback {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long feedbackId;

    // Long userId;
    // Long VehicleId;

    int rating;
    String comment;
    LocalDate feedbackDate;

    @ManyToOne
    @JoinColumn(name="user_idfeed", referencedColumnName = "user_id")
    private User userfeedback;

    @ManyToOne
    @JoinColumn(name="vehicle_idfeed", referencedColumnName = "vehicleId")
    private Vehicle vehiclefeed;
    
}
