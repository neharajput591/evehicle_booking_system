package com.example.evehicle_booking_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EvehicleBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvehicleBookingSystemApplication.class, args);

		System.out.println("e-Vehicle Booking System");
		System.out.println("Server port = 2111");

		// System.out.println(new BCryptPasswordEncoder().matches("admin", "$2a$10$K7eHcPOO0tbYqR6yk6mYqeSH3lfkI5Yqf4HjM4d1JqXJ9Qxk8vuIy"));

		System.out.println(new BCryptPasswordEncoder().encode("admin@123"));


	}

}
