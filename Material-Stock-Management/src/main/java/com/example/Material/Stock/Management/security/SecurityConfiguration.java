package com.example.Material.Stock.Management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//Security Configuration Class
//This class contains security related configuration
@Configuration
public class SecurityConfiguration {
    //Authentication
    //authentication with hard code users

  /*  @Bean
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

        //return the created users
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
    }  */

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config -> config
                        .antMatchers("/").hasAnyRole("SITEINCHARGE","STOREINCHARGE")
                        .antMatchers("/storeincharge", "/addmaterial", "/updatematerial", "/modifymaterial", "/viewmaterial").hasRole("STOREINCHARGE")
                        .antMatchers("/siteincharge","/request").hasRole("SITEINCHARGE")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return  http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
