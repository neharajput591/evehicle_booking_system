package com.example.evehicle_booking_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.evehicle_booking_system.UserModel.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select COUNT(*) AS order_id from Order")
    int countOfALlOrders();
    
}
