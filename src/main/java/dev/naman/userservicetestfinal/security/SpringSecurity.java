package dev.naman.userservicetestfinal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;

// @Configuration - tells that we will have many object which spring will create at the time of spring boot application starting
// all the methods which will have @Bean annotation will have an object created by spring i.e Dependency Injection
@Configuration
public class SpringSecurity {

    // when we add spring security dependency then spring will automatically authenticate all the api endpoints
    // thus in the below method we are premitting all api endpoints as of now
    // if we want to authenticate any endpoint as per our requirement then we have to
    // use the below commented code
    @Bean // it tells spring that an object of the below method will be created at the time when spring boot application starts
    @Order(1)
    public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/*").authenticated());
        return http.build();
    }
    // Object that handles what all api endpoints should be authenticated
    // v/s what all shouldn't be authenticated

    // in the below method we are return an object of BcryptPasswordEncoder because
    // in spring we donot have any special class of BcryptPasswordEncoder
    // as soon as spring boot application starts spring will not be able to create object of BcryptPasswordEncoder
    // thus we are providing a new object in the below method so that at the time of spring boot starting
    // an object of BcryptPasswordEncoder will be created.
    @Bean // it tells spring that an object of the below method will be created at the time when spring boot application starts
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
