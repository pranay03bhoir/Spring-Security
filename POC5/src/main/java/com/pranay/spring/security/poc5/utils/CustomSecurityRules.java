package com.pranay.spring.security.poc5.utils;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomSecurityRules {

    public boolean userAccessHandler(String username, Authentication authentication) {
        return authentication
                .getName()
                .equals(username)
                &&
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(
                                auth -> Objects.equals(auth
                                        .getAuthority(), "ROLE_ADMIN"));
    }

}
