package com.example.evehicle_booking_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evehicle_booking_system.UserModel.ServiceRequest;

public interface ServiceRepo extends JpaRepository<ServiceRequest, Long> {
    
}
