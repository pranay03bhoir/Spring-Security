package com.pranay.spring.security.poc5.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecurityController {

    // Demonstrating method level authorization and authentication filter.
    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping("/testMe/{username}")
    public String testGetV1DemoPoint(@PathVariable String username) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Access granted to user: " + username;
    }

    // Using custom security rules for method level security filter.
    @PreAuthorize("@customSecurityRules.userAccessHandler(#username,authentication)")
    @GetMapping("/testCustomHandler/{username}")
    public String testCustomHandler(@PathVariable String username) {
        return "Access granted to user: " + username;
    }

    // Using custom security rules for post-execution authorization.
    @PostAuthorize("returnObject == 'Access granted' and #username == authentication.name ")
    @GetMapping("/testPostHandler/{username}")
    public String testPostHandler(@PathVariable String username) {
        IO.println("This method is invoked, " + username);
        return "Access granted";
    }


    // Implementing multiline @PreAuthorise filter.
    @PreAuthorize("hasRole('ADMIN') and #username == authentication.name")
    @GetMapping("/testMultiline/{username}")
    public String testMultiline(@PathVariable String username) {
        return "Access granted to user: " + username;
    }

    @GetMapping("/testPreFilter")
    @PreFilter(value = "hasRole('ADMIN') and filterObject.contains('hacker')", filterTarget = "listToBeDeletedAndFiltered")
    public List<String> testPreFilter(@RequestBody List<String> listToBeDeletedAndFiltered) {
        // logic to get to DB and deleted the data and only admin can do that.
        return listToBeDeletedAndFiltered;
    }

    @GetMapping("/testPostFilter")
    @PostFilter("hasRole('ADMIN') and filterObject.contains('password')")
    public List<String> testPostFilter() {
        // logic to fetch data from DB and filter the data and only admin can do that.
        return List.of("password", "pranay", "code", "password_new");
    }

    @GetMapping("/status")
    public String status() {
        return "Server is running";
    }
}
