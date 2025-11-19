package com.example.evehicle_booking_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evehicle_booking_system.Repository.PaymentRepository;
import com.example.evehicle_booking_system.UserModel.Payment;


@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentrepo;

    public List<Payment> getOrderByUserId(Long userId){
        
        return paymentrepo.findByUserpaymentUserId(userId);
    }
    
}
