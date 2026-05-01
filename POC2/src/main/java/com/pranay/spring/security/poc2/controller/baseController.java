package com.pranay.spring.security.poc2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class baseController {

    @GetMapping("/baseEndpoint")
    public String baseHandler(){
        return "Hii Pranay Bhoir";
    }
}
