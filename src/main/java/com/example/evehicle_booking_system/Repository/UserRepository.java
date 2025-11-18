package com.example.evehicle_booking_system.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.evehicle_booking_system.UserModel.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.role=?1")
    public List<User> findusers(@Param("r") String r);

    @Query("SELECT COUNT(*) AS user_id FROM User")
    int summofallusers();
}
