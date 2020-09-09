package com.example.airbnblike.review.repository;

import com.example.airbnblike.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAllByRental_Id(long rental_id);
}
