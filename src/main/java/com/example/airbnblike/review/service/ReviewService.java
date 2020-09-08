package com.example.airbnblike.review.service;

import com.example.airbnblike.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
}
