package com.example.airbnblike.review.api;

import com.example.airbnblike.review.model.Review;
import com.example.airbnblike.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewApi {

    private final ReviewService reviewService;

    @Transactional
    @GetMapping("/rental/{rental_id}")
    public List<Review> getAllReviewsByRental (@PathVariable String rental_id) {
        return reviewService.getAllReviewsByRental(Long.parseLong(rental_id));
    }
}
