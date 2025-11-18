package com.example.evehicle_booking_system.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.evehicle_booking_system.Repository.UserRepository;
import com.example.evehicle_booking_system.UserModel.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // @Override
    // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //     User user = userRepository.findByEmail(email)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

    //     return org.springframework.security.core.userdetails.User.builder()
    //             .username(user.getEmail())
    //             .password(user.getPassword())
    //             .roles(user.getRole().replace("ROLE_", "")) // converts "ROLE_USER" → "USER"
    //             .build();
    // }

    @Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

    return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(user.getRole())   // ⭐ Use exact role from DB
            .build();
}

}
