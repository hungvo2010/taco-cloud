package com.starter.springboot.config;

import com.starter.springboot.model.User;
import com.starter.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class SecurityConfig {
    @Bean
    public PasswordEncoder choosePasswordEncoder() {
        return new BCryptPasswordEncoder(); // bcrypt strong hashing password
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            log.info(username);
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User " + username + " not found.");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                    .build();
    }
}
