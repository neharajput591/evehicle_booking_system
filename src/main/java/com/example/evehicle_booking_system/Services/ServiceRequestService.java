package com.example.evehicle_booking_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evehicle_booking_system.Repository.ServiceRepo;

@Service
public class ServiceRequestService {

    @Autowired
    ServiceRepo servrepo;

    
}
