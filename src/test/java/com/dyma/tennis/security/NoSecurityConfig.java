package com.dyma.tennis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Profile("test")
@Configuration
public class NoSecurityConfig {

    @Bean
    public WebSecurityCustomizer ignoringAll() {
        return web -> web.ignoring().requestMatchers("/**");
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> authentication; // pass-through
    }
}
