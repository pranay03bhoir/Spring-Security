package com.pranay.spring.security.poc3.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/test")
    public String testDemoEndpoint(){
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a -> System.out.println(a));
        return "Pranay Bhoir";
    }
}
