package com.pranay.spring.security.poc4.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/test")
    public String testDemoPoint() {
        SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .forEach(System.out::println);
        return "Pranay Bhoir";
    }
}
