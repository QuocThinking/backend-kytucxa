package com.apache.hotelroom.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // private final UserRepository userRepository;

    // @Bean
    // public UserDetailsService userDetailsService() {
    // return username ->
    // userRepository.findUserByUsername(username)
    // .orElseThrow(() -> new UsernameNotFoundException(
    // "Cannot find user with username: " + username
    // ));

    // };
    // Cấu hình PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình SecurityFilterChain thay thế cho WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu không cần
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll() // Cho phép tất cả các yêu cầu mà không cần xác thực
                );
        return http.build();
    }

    // @Bean
    // public AuthenticationProvider authenticationProvider(){
    // DaoAuthenticationProvider authenticationProvider = new
    // DaoAuthenticationProvider();
    // authenticationProvider.setUserDetailsService(userDetailsService());
    // authenticationProvider.setPasswordEncoder(passwordEncoder());
    // return authenticationProvider;
    // };

    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration
    // authenticationConfiguration) throws Exception {
    // return authenticationConfiguration.getAuthenticationManager();
    // }

}
