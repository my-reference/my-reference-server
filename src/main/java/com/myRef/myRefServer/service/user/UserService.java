package com.myRef.myRefServer.service.user;

import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Long signUp(UserRegisterDto requestDto) throws Exception;
//    ResponseEntity<UserLoginDto> logIn(UserLoginDto requestDto);
}
