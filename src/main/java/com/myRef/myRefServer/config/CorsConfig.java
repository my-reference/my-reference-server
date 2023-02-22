//package com.myRef.myRefServer.config;
//
//import jakarta.servlet.*;
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.io.IOException;
//
//@Configuration
//public class CorsConfig implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);  // 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정
//        config.addAllowedOrigin("*");      // 모든 IP에 응답을 허용
//        config.addAllowedHeader("*");      // 모든 header에 응답을 허용
//        config.addAllowedMethod("*");      // 모든 POST, GET, PATCH, PUT, DELETE 요청을 허용
//
//        source.registerCorsConfiguration("/api/**", config);
//    }
//}
