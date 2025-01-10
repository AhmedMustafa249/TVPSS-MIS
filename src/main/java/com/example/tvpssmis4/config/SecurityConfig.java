package com.example.tvpssmis4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.tvpssmis4.model.User;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .and()
                .withUser("salesdept")
                .password(passwordEncoder().encode("salesdept123"))
                .roles("SALES")
                .and()
                .withUser("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER");
    }
    // Add this PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for secure password encoding
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/register", "/login").permitAll()
                        .requestMatchers("/gpm/**").hasRole("GPM")
                        .requestMatchers("/ppd/**").hasRole("PPD")
                        .requestMatchers("/jpnj/**").hasRole("JPNJ")
                        .anyRequest().permitAll());
        return http.build();
    }
}
