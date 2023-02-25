package com.myRef.myRefServer.config.jwt;

import com.myRef.myRefServer.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Request Header 에서 JWT 토큰 추출
        String token = jwtTokenProvider.resolveToken((HttpServletRequest)request);
        System.out.println("token = " + token);
        try {
            // 2. validateAccessToken으로 Access 토큰 유효성 검사
            if (token != null && jwtTokenProvider.validateAccessToken(token)) {
                System.out.println("Valid access token");
                Authentication authentication = jwtTokenProvider.getAuthenticationByAccessToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);   // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            }
        } catch (CustomException e) {
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value(), e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }


    //    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 1. Request Header 에서 JWT 토큰 추출
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest)request);
//
//        try {
//            // 2. validateAccessToken으로 Access 토큰 유효성 검사
//            if (token != null && jwtTokenProvider.validateAccessToken(token)) {
//                Authentication authentication = jwtTokenProvider.getAuthenticationByAccessToken(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);   // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
//            }
//        } catch (CustomException e) {
//            SecurityContextHolder.clearContext();
//            response.
//        }
//
//        chain.doFilter(request, response);
//    }
}

