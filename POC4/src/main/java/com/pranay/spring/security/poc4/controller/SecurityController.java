package com.pranay.spring.security.poc4.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/testMe")
    public String testDemoPoint() {
        SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .forEach(System.out::println);
        return "GET - Pranay Bhoir";
    }

    @PostMapping("/testMe/v1")
    public String testDemoPointPost() {
        SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .forEach(System.out::println);
        return "POST - Pranay Bhoir";
    }

    @GetMapping("/status")
    public String status() {
        return "Server is running";
    }
}
