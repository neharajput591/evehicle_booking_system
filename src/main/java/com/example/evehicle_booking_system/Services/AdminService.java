package com.example.evehicle_booking_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.evehicle_booking_system.Repository.FeedbackRepository;
import com.example.evehicle_booking_system.Repository.OrderRepository;
import com.example.evehicle_booking_system.Repository.UserRepository;
import com.example.evehicle_booking_system.Repository.VehicleRepository;
import com.example.evehicle_booking_system.UserModel.User;

@Service
public class AdminService {

     @Autowired
    UserRepository ur;

    @Autowired
    VehicleRepository vr;

    @Autowired
    FeedbackRepository fr;

    @Autowired
    OrderRepository or;

    public List<User> finduser(@Param("r") String r){
        
        return ur.findusers(r);
    }

    public int getTotalVehicles(){
        return vr.sumtotalvehicles();
    }

    public int getTotalUsers(){
        return ur.summofallusers();
    }

    public int getTotalFeedback(){
        return fr.sumNoOfFeedback();
    }

    public int getTotalOrders(){
        return or.countOfALlOrders();
    }

    
}
