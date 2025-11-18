package com.example.evehicle_booking_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evehicle_booking_system.Repository.FeedbackRepository;
import com.example.evehicle_booking_system.UserModel.Feedback;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository fr;

    public List<Feedback> findfeedbacks(){
        return fr.findAll();
    }
    
}
