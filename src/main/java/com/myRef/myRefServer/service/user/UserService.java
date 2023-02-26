package com.myRef.myRefServer.service.user;

import com.myRef.myRefServer.config.jwt.JwtTokenDto;
import com.myRef.myRefServer.config.jwt.RegenarateTokenDto;
import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.domain.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Long signUp(UserRegisterDto requestDto) throws Exception;
    ResponseEntity<JwtTokenDto> logIn(UserLoginDto requestDto);
    ResponseEntity<JwtTokenDto> regenerateToken(RegenarateTokenDto refreshTokenDto);
    Optional<User> findByEmail(String email);
}
