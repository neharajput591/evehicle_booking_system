package com.example.evehicle_booking_system.Services;

import java.util.List;
import java.util.Optional;

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

    public Optional<Payment> getPaymentById(Long orderId){

        return paymentrepo.findByPaymentId(orderId);

    }

    // public static Payment getPaymentById(Long orderId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getPaymentById'");
    // }
    
}
