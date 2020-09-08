package com.example.airbnblike.review.api;

import com.example.airbnblike.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewApi {

    private final ReviewService reviewService;
}
