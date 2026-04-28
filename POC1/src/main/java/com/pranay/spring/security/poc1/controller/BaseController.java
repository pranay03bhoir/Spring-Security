package com.pranay.spring.security.poc1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/baseEndpoint")
    public String baseHandler(){
        return "Hii Pranay Bhoir";
    }
}
