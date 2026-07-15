package com.pranay.spring.security.poc5.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

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

    @GetMapping("/status")
    public String status() {
        return "Server is running";
    }
}
