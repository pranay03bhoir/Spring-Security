package com.pranay.spring.security.poc4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    String expression = "isAuthenticated() and hasAuthority('read') or hasAuthority('write')";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request ->
                                request
//                                        .anyRequest().permitAll()
//                                .hasAuthority("USER")
//                                 .hasAnyAuthority("read", "write")
//                                        .hasRole("ADMIN")
//                                        .hasAnyRole("USER", "ADMIN")
//                                        .access(new WebExpressionAuthorizationManager(expression))
                                        .requestMatchers("/status").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/test/**").hasRole("USER")
                                        .requestMatchers(HttpMethod.POST, "/test/**").hasRole("ADMIN")
                                        .anyRequest().authenticated()
//                                        .anyRequest().denyAll()
                )
                .build();
    }


    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetailsObj1 = User
                .withUsername("pranay")
                .password("pranay123")
                .authorities("read")
                .roles("USER")
                .build();
        UserDetails userDetailsObj2 = User
                .withUsername("manisha")
                .password("manisha123")
                .authorities("write")
                .roles("ADMIN")
                .build();
        inMemoryUserDetailsManager.createUser(userDetailsObj1);
        inMemoryUserDetailsManager.createUser(userDetailsObj2);
        return inMemoryUserDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
