package com.example.airbnblike.review.service;

import com.example.airbnblike.review.model.Review;
import com.example.airbnblike.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public List<Review> getAllReviewsByRental(Long rental_id){
        return reviewRepository.findAllByRental_Id(rental_id);
    }
}
