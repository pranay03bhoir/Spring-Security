package com.pranay.spring.security.poc4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Demonstrating method level authorization and authentication filter.
    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping("/testMe/{username}")
    public String testGetV1DemoPoint(@PathVariable String username) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Access granted to user: " + username;
    }

    // Implementing multiline @PreAuthorise filter.
    @PreAuthorize("hasRole('ADMIN') and #username == authentication.name")
    @GetMapping("/testMultiline/{username}")
    public String testMultiline(@PathVariable String username) {
        return "Access granted to user: " + username;
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
