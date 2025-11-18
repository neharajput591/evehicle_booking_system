package com.example.evehicle_booking_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.evehicle_booking_system.UserModel.Vehicle;

@Repository
public interface  VehicleRepository extends JpaRepository<Vehicle, Long>{

    Optional<Vehicle> findByModelName(String modelName);

    public void deleteByModelName(String modelName);

    @Query("SELECT COUNT(*) AS vehicle_id FROM Vehicle")
    int sumtotalvehicles();
    
}
