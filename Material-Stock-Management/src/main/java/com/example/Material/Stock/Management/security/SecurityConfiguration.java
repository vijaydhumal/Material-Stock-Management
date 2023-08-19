package com.example.Material.Stock.Management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    //Create external users with hardcode values

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        //creates two users with roles
        UserDetails shankar = User.builder()
                .username("shankar")
                .password("{noop}123")
                .roles("SITEINCHARGE")
                .build();

        UserDetails nikhil = User.builder()
                .username("nikhil")
                .password("{noop}456")
                .roles("STOREINCHARGE")
                .build();

        return new InMemoryUserDetailsManager(shankar, nikhil);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure security settings
        http.authorizeRequests(config->
                        //Authontication part identify users based on logging credentials
                        config
                                .antMatchers("/").hasAnyRole("SITEINCHARGE","STOREINCHARGE")
                                .antMatchers("/siteincharge").hasRole("SITEINCHARGE")
                                .antMatchers("/storeincharge").hasRole("STOREINCHARGE")
                                .anyRequest().authenticated())
                // Configure form-based login
                .formLogin(Customizer.withDefaults());
        return http.build();
    }


}
