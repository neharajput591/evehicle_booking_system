package com.example.evehicle_booking_system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evehicle_booking_system.UserModel.CartItem;
import com.example.evehicle_booking_system.UserModel.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
     List<CartItem> findByUser(User user);
}
