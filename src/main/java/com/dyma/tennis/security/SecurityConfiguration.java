package com.dyma.tennis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private DymaUserDetailsService DymaUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(DymaUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(csrf -> csrf.disable())
                .headers(header ->
                        header
                                .contentSecurityPolicy(csp ->
                                    csp.policyDirectives("default src 'self' data:;style-src 'self' 'unsafe-inline';")
                                )
                                .frameOptions(frameOptions -> frameOptions.deny())
                                .permissionsPolicyHeader(permissionsPolicyConfig ->
                                    permissionsPolicyConfig.policy("fullscreen=(self), geolocation=(), microphone=(), camera=()")
                                )
                )
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(HttpMethod.GET, "/players/**").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/players/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/players/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/players/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/healthcheck/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/accounts/login").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
