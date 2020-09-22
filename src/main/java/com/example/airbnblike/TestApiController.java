package com.example.airbnblike;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestApiController {

    private final DataInsertion dataInsertion;

    @GetMapping("/add-data")
    public void addData(){
        dataInsertion.addData3();
    }
}
