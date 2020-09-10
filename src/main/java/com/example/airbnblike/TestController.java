package com.example.airbnblike;

import com.example.airbnblike.rental.dto.RentalDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String testView(Model model) {
        model.addAttribute("rentalDto", new RentalDto());
        return "test_view";
    }
}
