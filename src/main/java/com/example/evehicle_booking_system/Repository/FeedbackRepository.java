package com.example.evehicle_booking_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.evehicle_booking_system.UserModel.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    

    @Query("select COUNT(*) AS feedback_id from Feedback")
    int sumNoOfFeedback();
}
