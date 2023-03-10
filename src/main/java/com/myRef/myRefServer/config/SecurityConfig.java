package com.myRef.myRefServer.config;

import com.myRef.myRefServer.config.jwt.JwtAccessDeniedHandler;
import com.myRef.myRefServer.config.jwt.JwtAuthenticationEntryPoint;
import com.myRef.myRefServer.config.jwt.JwtSecurityConfig;
import com.myRef.myRefServer.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final CorsConfig corsConfig;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // Disable csrf to use token
            http
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .authorizeHttpRequests(request -> request
                            .requestMatchers(
                                    "/",
                                    "/v1/signup",
                                    "/v1/login*",
                                    "/v1/token/refresh").permitAll()
                            .anyRequest().authenticated()
                    );

            // No session will be created or used by spring security
            http
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                    .and()
//                        .addFilterBefore(doFilter);


            // exception handling for jwt
            http
                    .exceptionHandling()
                    .accessDeniedHandler(jwtAccessDeniedHandler)
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint);

            // Apply JWT
            http.apply(new JwtSecurityConfig(jwtTokenProvider));

        return http.build();
    }
}
