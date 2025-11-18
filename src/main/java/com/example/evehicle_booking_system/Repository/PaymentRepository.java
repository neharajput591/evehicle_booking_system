package com.example.evehicle_booking_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.evehicle_booking_system.UserModel.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Optional<Payment> findByModelname(String modelname);
    // @Query("select p from Payment p where p.modelname=?1")
    // public Optional<Payment> findByModelname(@Param("modelname") String
    // modelname);

    @Query("SELECT p FROM Payment p WHERE p.modelname = ?1")
    Optional<Payment> findByModelname(String modelname);

    // @Query("SELECT pay FROM Payment pay WHERE pay.modelname=?1 AND pay.user_id=?2")
    // Optional<Payment> findByModelnameAndUser_id(String modelname , Long userid);

}
